#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>
#include <Wire.h> 

const char *ssid = "*****";            // Nazwa sieci Wi-Fi, do ktorej nastapi polaczenie
const char *password = "*******";      // Haslo do niej
 
const char *host = "iotprojectteam.000webhostapp.com";  // serwer
const int httpsPort = 443;                              // serwer na https, port 443           
const char fingerprint[] = "5B FB D1 D4 49 D3 0F A9 C6 40 03 34 BA E0 24 05 AA D2 E2 01"; // fingerprint SHA-1 strony

int ntuValueReceived = 0;  // inicjalizacja odczytu z czujnika
String deviceID = "1";           // device ID (numer czujnika)
int sendDelay = 30;              // co ile dane beda wysylane w sekundach

void wifiConnect() // funkcja laczaca sie z siecia wi-fi
{
  Serial.begin(9600);             // serial monitor do debugowania
  Serial.setTimeout(2000);  
  WiFi.mode(WIFI_STA);            // ukrywa Wi-Fi jako hotspot
  Wire.begin(D1, D2);             // inicjalizacja interfejsu I2C do przesyłu danych z Arduino (odczyty czujnika)
  
  WiFi.begin(ssid, password);     // polacz z siecia Wi-Fi
  Serial.println("");
 
  Serial.print("Connecting ");
  while (WiFi.status() != WL_CONNECTED) 
  {
    delay(100);
    Serial.print(".");
  } 
  Serial.println("");
  Serial.print("Success! IP: ");
  Serial.println(WiFi.localIP());  // adres IP przypisany do ESP
  Serial.println("");
}

int requestData() // funkcja odbierajaca od arduino dane z czujnika
{
  Wire.requestFrom(8, 2); // I2C - wyslanie prosby do slave'a o adresie 8 o przesyl 2 bajtowych danych (max odczyt z czujnika to 3000, stad wystarcza 2 bajty)
  while(Wire.available())
  {
    ntuValueReceived = Wire.read();        // LSB
    ntuValueReceived += Wire.read()*256;   // MSB
  }
  return ntuValueReceived;
}

void sendDataToServer(int ntu) // funkcja laczaca sie z serwerem i wysylajaca dane
{
  WiFiClientSecure https;   
  https.setFingerprint(fingerprint);     // ustawienie podanego fingerprinta
  https.setTimeout(15000);

  Serial.println("Connecting to server ");
  while((!https.connect(host, httpsPort)))
  {
      delay(100);
      Serial.print(".");
  }
  Serial.println("");
  
  String ntuValue = String(ntu);
  String php = "/NewServer/addToDB.php?deviceID="+deviceID+"&ntuValue="+ntuValue;

  https.print(String("POST ") + php + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: close\r\n\r\n");
  Serial.println("Data sent!");
}

void setup() 
{
  wifiConnect();
  requestData();
  Serial.println(ntuValueReceived);
  sendDataToServer(ntuValueReceived);
  ESP.deepSleep(sendDelay*10e5); // wprowadzenie ESP w tryb glebokiego snu - oszczedzanie energii
}
 
void loop(){} // brak petli, deep sleep resetuje modul
