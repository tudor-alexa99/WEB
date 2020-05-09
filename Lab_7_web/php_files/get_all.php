<?php

    require_once '../php_files/employee.php';

    $connection = new mysqli("localhost", "root", "", "my_enterprise");
    $rows = $connection->query("SELECT * FROM employees");

    
    $output = array();

    while($row  = $rows->fetch_assoc()){
        $employee = new Employee($row['id'], $row['f_name'], $row['l_name'], $row['position'], $row['salary']);


        array_push($output, $employee);
    }
    $connection->close();
    echo json_encode($output);

?>