<?php
    header('Content-Type: application/octet-stream');
    header('Content-Disposition: attachment; filename='.basename('ourIoTData.txt'));
    header('Expires: 0');
    header('Cache-Control: must-revalidate');
    header('Pragma: public');
    header('Content-Length: ' . filesize('ourIoTData.txt'));
    
    $configPath = realpath(dirname(__FILE__));
    require_once($configPath."/config.php");

    $connection = @new mysqli(dbToken_host_name,dbToken_username,dbToken_password,dbToken_name);

    if(mysqli_connect_errno() != 0)
    {
        echo("Error! Can't connect with DB heroku");
    }
    else
    {
        $sql = "SELECT ID,deviceID,ntuValue,timestamp FROM sensordata";
        $result = $connection->query($sql);
        $tokenCorrect = false;

        if($result == true)
        {
            $myFile = fopen("ourIoTData.txt", "w") or die  ("Nie mozna otworzyc pliku!");

            while(($read = $result -> fetch_assoc()) !== NULL) // po jednym wierszu odczytujemy kolejne tabele ASOCJACYJNIE (ntuValue, ID przy jednym odczycie)
            {
                fwrite($myFile, $read['ID']);
                fwrite($myFile, " ");
                fwrite($myFile, $read['deviceID']);
                fwrite($myFile, " ");
                fwrite($myFile, $read['ntuValue']);
                fwrite($myFile, " ");
                fwrite($myFile, $read['timestamp']);
                fwrite($myFile,"\r\n");
            }
            fclose($myFile);
           
            $result -> close();
        }
        else
        {
            $result -> close();   
            echo("Error while query asking! Try again or contact me (MJ)");
        }
     $connection->close();
     readfile('ourIoTData.txt');  
    }
    
?>