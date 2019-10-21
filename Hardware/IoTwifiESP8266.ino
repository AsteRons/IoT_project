#include <SoftwareSerial.h>

SoftwareSerial wifi(8,9);

void setup() 
{
  Serial.begin(9600);
  wifi.begin(9600); 
}

void loop() 
{
  String dana = ""; 

  wifi.println("AT\r\n");
  delay(100);
  
  while(wifi.available()) // Odbiór danych od modułu - może się przydać
  {
    dana=dana + wifi.readString();
  }
  Serial.println("Odebrano:");
  Serial.println(dana);
}
