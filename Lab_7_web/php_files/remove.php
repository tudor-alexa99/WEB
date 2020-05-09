<?php
    print "php add";
    $connection = new mysqli("localhost", "root", "", "my_enterprise");
    $id = $_POST['id_get'];

    echo "id: " . $id;
    $query_text = "delete from employees where id = " . $id;
    $connection->query($query_text);
    echo $query_text;
    $connection->close();
?>