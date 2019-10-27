<?php
    $connection = @new mysqli('us-cdbr-iron-east-05.cleardb.net','b4d95917bcb107','705523ca','heroku_ac0c70f9d552a1b');

    if(mysqli_connect_errno() !=0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        $sql = "SELECT ID,deviceID,ntuValue,timestamp FROM sensordata";

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