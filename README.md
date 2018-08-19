# MyFirstApp

First, you have to create schema "test" and tabel "employees" in MySQL :

CREATE SCHEMA IF NOT EXISTS test;
  use test;

CREATE TABLE employees (
`id` INT AUTO_INCREMENT,PRIMARY KEY (`id`),
`first_name` VARCHAR(50) DEFAULT '',
`last_name` VARCHAR(30) DEFAULT '',
`email` VARCHAR(30) DEFAULT '' ,
`password` VARCHAR(50) DEFAULT ''
) 

