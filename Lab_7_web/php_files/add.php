<?php

    // require_once '../php_files/employee.php';
    $connection = new mysqli("localhost", "root", "", "my_enterprise");
    $output = $connection->query("select max(id) as max_id from employees");
    $id = $output->fetch_assoc()['max_id'] + 1;
    $f_name = $_POST['f_name_get'];
    $l_name = $_POST['l_name_get'];
    $position = $_POST['position_get'];
    $salary = $_POST['salary_get'];

    echo "id: " . $id;

    $connection->query("insert into employees(id, f_name, l_name, position, salary) values ($id, '$f_name', '$l_name', '$position', $salary)");
    $connection->close();
?>