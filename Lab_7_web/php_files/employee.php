<?php

    class Employee implements JsonSerializable{
        private $id;
        private $f_name;
        private $l_name;
        private $position;
        private $salary;

        public function __construct($id, $f_name, $l_name, $position, $salary){
            $this->id = $id;
            $this->f_name = $f_name;
            $this->l_name = $l_name;
            $this->position = $position;
            $this->salary = $salary;
        }

        public function jsonSerialize(){
            return [
                'id' => $this->id,
                'f_name' => $this->f_name,
                'l_name' => $this->l_name,
                'position' => $this->position,
                'salary' => $this->salary
            ];
        }
    } 

?>