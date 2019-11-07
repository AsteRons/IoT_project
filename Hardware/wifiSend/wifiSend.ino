#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>
#include <Wire.h> 

#define   WIFI_SSID     "UPCD3BCEBC"                       // Nazwa sieci Wi-Fi, do ktorej nastapi polaczenie
#define   WIFI_PASS     "sWspstcxamA7"                     // Haslo do niej
#define   HOST          "iotprojectteam.000webhostapp.com" // serwer
#define   PORT          443                                // port, https = 443, http = 80
#define   FINGERPRINT   "5B FB D1 D4 49 D3 0F A9 C6 40 03 34 BA E0 24 05 AA D2 E2 01"  // fingerprint SHA-1 strony
#define   SEND_DELAY    30                                 // co ile beda wysylane dane (w sekundach)

int ntuValueReceived = 0;  // inicjalizacja odczytu z czujnika
String deviceID = "1";     // device ID (numer czujnika)

void wifiConnect() 
// funkcja laczaca sie z siecia wi-fi //
{
  Serial.begin(9600);            
  Serial.setTimeout(2000);  
  WiFi.mode(WIFI_STA);            // ukrywa Wi-Fi jako hotspot
  Wire.begin(D1, D2);             // inicjalizacja interfejsu I2C do przesyłu danych z Arduino (odczyty czujnika)
  
  WiFi.begin(WIFI_SSID, WIFI_PASS);
  Serial.println("");
 
  Serial.print("Connecting ");
  while (WiFi.status() != WL_CONNECTED) 
  {
    delay(100);
    Serial.print(".");
  } 
  Serial.println("");
  Serial.print("Success! IP: ");
  Serial.println(WiFi.localIP());
  Serial.println("");
}

int requestData() 
// funkcja odbierajaca od arduino dane z czujnika //
{
  Wire.requestFrom(8, 2); // I2C - wyslanie prosby do slave'a o adresie 8 o przesyl 2 bajtowych danych (max odczyt z czujnika to 3000, stad wystarcza 2 bajty)
  while(Wire.available())
  {
    ntuValueReceived = Wire.read();        // LSB
    ntuValueReceived += Wire.read()*256;   // MSB
  }
  return ntuValueReceived;
}

void sendDataToServer(int ntu) 
// funkcja laczaca sie z serwerem i wysylajaca dane //
{
  WiFiClientSecure https;   
  https.setFingerprint(FINGERPRINT);
  https.setTimeout(15000);

  Serial.println("Connecting to server ");
  while((!https.connect(HOST, PORT)))
  {
      delay(100);
      Serial.print(".");
  }
  Serial.println("");
  
  String ntuValue = String(ntu);
  String php = "/NewServer/addToDB.php?deviceID="+deviceID+"&ntuValue="+ntuValue;

  https.print(String("POST ") + php + " HTTP/1.1\r\n" +
               "Host: " + HOST + "\r\n" +
               "Connection: close\r\n\r\n");
  Serial.println("Data sent!");
}

void setup() 
{
  wifiConnect();
  requestData();
  Serial.println(ntuValueReceived);
  sendDataToServer(ntuValueReceived);
  ESP.deepSleep(SEND_DELAY*10e5); // wprowadzenie ESP w tryb glebokiego snu
}
 
void loop(){} // brak petli, deep sleep resetuje modul
