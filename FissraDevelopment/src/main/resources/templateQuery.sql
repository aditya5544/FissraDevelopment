-- Create Database
CREATE SCHEMA `template_data` ;
use  template_data;

-- Create Tables
-- Create Area table
create table area(area_id int primary key auto_increment ,area_name varchar(20),region_id int,area_manager_id int,status varchar(20));

-- Create Region Table
create table region(region_id int primary key auto_increment,region_name varchar(20),region_manager varchar(20));

 -- Create  Template Detailes Table for Database Name
create table template_details(details_id int auto_increment primary key,details varchar(20));
insert into template_details (details) values("Region");
insert into template_details (details) values("Area");

-- Create table for file_details
create table file_details(file_id int auto_increment primary key,file_name varchar(20), fileupload_date date,status varchar(20),action varbinary(200) );




CREATE TABLE `databasDetails` (
  `sr_no` INT NOT NULL AUTO_INCREMENT,
  `database_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sr_no`) );

  
  
-- Create users table
CREATE TABLE `users` (
  `user_id` INT NOT NULL auto_increment,
  `first_name` VARCHAR(45) NOT NULL, 
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`));
  
  -- Inser Data into user
  INSERT INTO `template_data`.`users` (`user_id`, `first_name`, `last_name`, `username`, `password`) VALUES ('101', 'Aditya', 'Kadam', 'aditya', 'aditya123');
  INSERT INTO `template_data`.`users` (`user_id`, `first_name`, `last_name`, `username`, `password`) VALUES ('102', 'Pooja', 'Singh', 'pooja', 'pooja123');


  
  
  
 --  select * from databasDetails;
--   delete  from databasDetails where sr_no=4;
