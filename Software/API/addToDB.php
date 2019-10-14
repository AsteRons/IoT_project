<?php

    $connection = @new mysqli('localhost','id11175903_iotproject','iotproject','id11175903_iotproject'); // @ - error protection

    if(mysqli_connect_errno() != 0) // Ostatnie polaczanie - zwracany jest stan
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        if($_GET['deviceID']==true && $_GET['ntuValue']==true)
        {
            $devID = $_GET['deviceID'];
            $ntuVal = $_GET['ntuValue'];
            $connection -> query("SET time_zone='+02:00'"); // set time zone
            $sql = "INSERT INTO ntuTable (deviceID,ntuValue,timestamp) VALUES ('$devID','$ntuVal',NOW())";
            $result = $connection->query($sql);

            if($result == true)
            {
                echo("Data added succesfully!");
            }
            else
            {
                echo("Error while adding data! Try again or contact me (MJ)");
            }
        }
        else
        {
            echo("Error! You have to set all parameters!");
        }
        
        $connection->close();
    }

?>

