use hospital_management_system;
-- create table hospital_management_system.doctor (
-- 	doctorId int auto_increment primary key,
--     doctorName varchar(30),
--     doctorDepartment varchar(20),
--     phone varchar(15),
--     emailId varchar(20),
--     doctorPassword varchar(255),
--     doctorConfirmPassword varchar(255)
-- );
-- alter table doctor
-- 	add address varchar(50) after doctorName;
-- INSERT INTO hospital_management_system.doctor
-- (doctorName, doctorDepartment, phone, emailId, doctorPassword, doctorConfirmPassword)
-- VALUES
-- ('Dr. Arjun Mehta', 'Cardiology', '9876543210', 'arjun.mehta@hms.com', 'Arjun@123', 'Arjun@123');
truncate doctor;
select * from hospital_management_system.doctor;  
