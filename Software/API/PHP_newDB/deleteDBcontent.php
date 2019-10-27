<?php
    $connection = @new mysqli('us-cdbr-iron-east-05.cleardb.net','b4d95917bcb107','705523ca','heroku_ac0c70f9d552a1b');

    if(mysqli_connect_errno() != 0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        $sql = "DELETE FROM sensordata";
        $result = $connection->query($sql);

        if($result == true)
        {
            echo("All data has been deleted succesfully!");
            $connection -> query("ALTER TABLE sensordata AUTO_INCREMENT = 1");
        }
        else
        {
            echo("Error while deleting data!");
        }
    }
?>