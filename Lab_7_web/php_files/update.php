<?php

    // require_once '../php_files/employee.php';
    $connection = new mysqli("localhost", "root", "", "my_enterprise");
    $id = $_POST['id_get'];
    $f_name = $_POST['f_name_get'];
    $l_name = $_POST['l_name_get'];
    $position = $_POST['position_get'];
    $salary = $_POST['salary_get'];

    echo "id: " . $id;

    $connection->query("update employees set f_name = '$f_name', l_name = '$l_name', position = '$position', salary = $salary where id = $id");
    $connection->close();
?>