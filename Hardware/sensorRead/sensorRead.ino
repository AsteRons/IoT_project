#include <Wire.h>

int ntuValue = 0;

void requestEvent() // zdarzenie wysylane przez ESP, powodujace wysyl danych z Arduino
{ 
  byte buf[2]; // bufor o rozmiarze 2 bajtow (max odczyt czujnika to 3000)
  buf[0] = ntuValue & 255;
  buf[1] = (ntuValue >> 8) & 255; // przesuniecie bitowe
  Wire.write(buf, 2);
}

void setup() 
{
  Wire.begin(8);   // adres 8
  Wire.onRequest(requestEvent);
  Serial.begin(9600);
}

void loop() // odczyt danych z czujnika oraz przeliczenie napieca na jednostke NTU (nephelometric turbidity units)
{
  float voltage = 0;
  int sensorValue = analogRead(A0);
  voltage = sensorValue * (5.0 / 1024.0);
  if(voltage<2.5) // zgodnie z danymi producenta
  {
    ntuValue=3000;
  }
  else
  {
    ntuValue=-1120.4*voltage*voltage+5742.3*voltage-4352.9; // zgodnie z danymi producenta
  }  
  ntuValue = (int)ntuValue;
  Serial.println(ntuValue);
  delay(1000);
}
