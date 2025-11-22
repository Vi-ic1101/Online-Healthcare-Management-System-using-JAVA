use hospital_management_system;
-- create table hospital_management_system.appointment (
-- 	doctorId int auto_increment primary key,
--     doctorName varchar(30),
--     doctorDepartment varchar(20),
--     docAppointmentDate date,
--     docAppointpentTime time,
--     docAppointpentStatus varchar(20)
		
        alter table appointment
			add patientName varchar(30) after doctorDepartment,
--     doctorName varchar(30),
-- 	doctorDepartment varchar(20),
-- 	patientAppointmentDate date,
--     patientAppointpentTime time,
--     patientAppointpentStatus varchar(20)
-- );

select * from hospital_management_system.appointment;  
