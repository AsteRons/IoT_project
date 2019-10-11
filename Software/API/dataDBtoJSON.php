<?php
    $connection = @new mysqli('localhost','id11175903_iotproject','iotproject','id11175903_iotproject');

    $DBdata = array();
    //$jsonData = array();

    if(mysqli_connect_errno() != 0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        $sql = "SELECT ID,deviceID,ntuValue,timestamp FROM ntuTable";  
        $result = $connection->query($sql);

        if($result->num_rows >0)
        {
            while($row = $result->fetch_assoc())
            {
                $DBdata[] = $row;
            }
            echo json_encode($DBdata);
            print json_encode($DBdata);
            echo("Data added to JSON format!");
        }
        else
        {
            echo("No data in database!");
        }

        $connection->close();
    }
?>