#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h> 

const char *ssid = "**********";            // Nazwa sieci Wi-Fi, do ktorej nastapi polaczenie
const char *password = "************";      // Haslo do niej
 
const char *host = "iotprojectteam.000webhostapp.com";  // serwer
const int httpsPort = 443;                              // serwer stoi na https, port 443          
 
const char fingerprint[] = "5B FB D1 D4 49 D3 0F A9 C6 40 03 34 BA E0 24 05 AA D2 E2 01"; // fingerprint https strony

String deviceID = "1";   // device ID (czujnik)
int sendDelay = 300000;  // co ile dane beda wysylane w milisekundach (domyslnie co 5min)
 
void setup() 
{
  Serial.begin(115200);  
  WiFi.mode(WIFI_STA);            // ukrywa Wi-Fi jako hotspot
  
  WiFi.begin(ssid, password);     // polacz z siecia Wi-Fi uzywajac danych z poczatku
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
 
void loop() {
  WiFiClientSecure https;   
  https.setFingerprint(fingerprint);        // ustawienie podanego fingerprinta
  https.setTimeout(15000);

  Serial.println("Connecting to server ");
  while((!https.connect(host, httpsPort)))
  {
      delay(100);
      Serial.print(".");
  }
  Serial.println("");

  // Tutaj odbywa sie cala procedura przypisania ID czujnika, odczytu z niego oraz wyslanie danych poprzez aktywacje skryptu
  String ntuValue = "321";
  String php = "/NewServer/addToDB.php?deviceID="+deviceID+"&ntuValue="+ntuValue;

  https.print(String("POST ") + php + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: close\r\n\r\n");

  Serial.println("Data sent!");
  delay(sendDelay);
}
