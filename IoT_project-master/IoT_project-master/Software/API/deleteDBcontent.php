<?php
    $connection = @new mysqli('localhost','id11175903_iotproject','iotproject','id11175903_iotproject');

    if(mysqli_connect_errno() != 0)
    {
        echo("Error, unable to connect choosen database!");
    }
    else
    {
        echo("Connection succesful!");

        $sql = "DELETE FROM ntuTable";
        $result = $connection->query($sql);

        if($result == true)
        {
            echo("All data has been deleted succesfully!");
            $connection -> query("ALTER TABLE ntuTable AUTO_INCREMENT = 1");
        }
        else
        {
            echo("Error while deleting data!");
        }
    }
?>