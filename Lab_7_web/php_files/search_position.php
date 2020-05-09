<?php

    require_once '../php_files/employee.php';

    $position = $_GET["search_position"];
    $connection = new mysqli("localhost", "root", "", "my_enterprise");
    $rows = $connection->query("select * from employees e where e.position like '%$position%'");

    $output = array();

    while($row  = $rows->fetch_assoc()){
        $employee = new Employee($row['id'], $row['f_name'], $row['l_name'], $row['position'], $row['salary']);


        array_push($output, $employee);
    }
    $connection->close();
    echo json_encode($output);

?>