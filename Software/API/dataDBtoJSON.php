<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");

    $connection = @new mysqli('localhost','id11175903_iotproject','iotproject','id11175903_iotproject');

    $DBdata = array();
    //$jsonData = array();

    if(mysqli_connect_errno() != 0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        $sql = "SELECT ID,deviceID,ntuValue,timestamp FROM ntuTable";  
        $result = $connection->query($sql);

        if($result->num_rows >0)
        {
            while($row = $result->fetch_assoc())
            {
                $DBdata[] = $row;
            }
            print json_encode($DBdata);

            $result->close();
            $connection->close();
        }
        else
        {
            echo("No data in database!");
        }
    }
?>
