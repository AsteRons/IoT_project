#include <EEPROM.h>
 
int addr = 0; // adres pamieci
 
void setup()
{
  EEPROM.begin(512);  
  String token = "TUTAJ WPISAC TOKEN";
  for(int i=0; i < token.length(); i++) // petla do zapisu
  {
    EEPROM.write(0+i,token[i]); 
  }
  EEPROM.commit();   
}
 
void loop()
{}
