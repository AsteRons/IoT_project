void setup() 
{
  Serial.begin(9600);
}
void loop() 
{
  float voltage, ntuValue;
  int sensorValue = analogRead(A0);
  voltage = sensorValue * (5.0 / 1024.0);
  if(voltage<2.5)
  {
    ntuValue=3000;
  }
  else
  {
    ntuValue=-1120.4*voltage*voltage+5742.3*voltage-4352.9;
  }
  Serial.print(ntuValue);
  Serial.print("      ");
  Serial.println(voltage);
  delay(500);
}
