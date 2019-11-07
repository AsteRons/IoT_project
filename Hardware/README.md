------------------------------

Dane ogólne:
W projekcie wykorzystano czujnik mętności wody SEN0189. Dane z niego odczytuje i przetwarza zwykłe Arduino Uno. Podaje je dalej do modułu Wi-Fi ESP8266 NodeMcu V3 ESP12-E.
Do zaprogramowania ESP8266 użyto Arduino IDE z biblioteką ESP8266 by ESP8266 community version 2.5.2.

------------------------------

Schemat połączeń:

Arduino -----  ESP

  GND  ------  GND

  A5  ------    D2

  A4  ------    D1

ESP D0 - RST zwarte (na czas flashowania ESP rozewrzeć, ewentualnie po flashu manualnie zresetować ESP)



Arduino ----  Czujnik

  GND  -----   GND

  5V   -----   VCC

  A0   -----   Analog Output

------------------------------

W razie błędnych odczytów z czujnika należy go skalibrować. Czujnik powinien wskazywać wartość ok. 4.2 [V] w czystej wodzie (~ 0 NTU).
W celu kalibracji należy delikatnie zdjąć czarną osłonkę na czujniku (podważyć 4 zatrzaski). Następnie wyjąć czujnik i zlokalizować mały dzielnik napięcia. Po wstępnym skalibrowaniu czujnik umieścić z powrotem w pojemniku ochronnym (należy się upewnić, że czujnik wszedł maksymalnie, bez użycia nadmiaru siły, do środka - odczyty z niego są różne przy różnym jego umiejscowieniu w pojemniku), a następnie sprawdzić odczyt poprzez umieszczenie go w wodzie. W razie dalszych odchyłów czynność powtarzać do skutku (wyjąć czujnik -> przekręcić dzielnik -> włożyć czujnik -> test w wodzie). Po skalibrowaniu założyć czarną osłonkę ponownie. W razie dalszych odchyłów - powtórzyć kalibrację lub w przypadku niewielkich odchyłów - skalibrować software'owo.
