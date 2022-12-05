use  template_data;
create table area(area_id int primary key auto_increment ,area_name varchar(20),region_id int,area_manager_id int,status varchar(20));
create table region(region_id int primary key auto_increment,region_name varchar(20),region_manager varchar(20));
create table template_details(details_id int auto_increment primary key,details varchar(20));
create table file_details(file_id int auto_increment primary key,file_name varchar(20), fileupload_date date,status varchar(20),action varbinary(200) )
select * from template_details;
insert into template_details (details) values("Region");

CREATE TABLE `databasDetails` (
  `sr_no` INT NOT NULL AUTO_INCREMENT,
  `database_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sr_no`));
  Drop table DbDetails;
  
  
  select * from databasDetails;
  delete  from databasDetails where sr_no=4;
