<?php
    $connection = @new mysqli('localhost','id11175903_iotproject','iotproject','id11175903_iotproject');

    if(mysqli_connect_errno() !=0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        $sql = "SELECT ID,deviceID,ntuValue,timestamp FROM ntuTable";

        $result = $connection->query($sql);

        if($result == true)
        {
            echo("Query succesful."."<br>");

            if($result->num_rows > 0)
            {
                while($row = $result->fetch_assoc())
                {
                    echo("ID:".$row["ID"]. " deviceID:".$row["deviceID"]." ntuValue:".$row["ntuValue"]." timestamp:".$row["timestamp"]);
                    echo("<br>");
                }
            }
            else
            {
                echo("No rows in DB table!");
            }
        }
        else
        {
            echo("Error while query asking! Try again or contact me (MJ)");
        }

        $connection->close();
    }
?>