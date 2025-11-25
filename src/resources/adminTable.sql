

 DESCRIBE hospital_management_system.admin;
-- -- 
alter table hospital_management_system.admin
	add adminName varchar(255),
	add address varchar(25),
	add phone varchar(255),
	add emailID varchar(255),
	add adminPassword varchar(255),
	add adminConfirmPassword	varchar(255);
 INSERT INTO hospital_management_system.admin
 (adminName, address, phone, emailID, adminPassword, adminConfirmPassword)
 VALUES
('Ramesh Kumar', '123 MG Road, Delhi', '9876543210', 'ramesh.kumar@example.com', 'Admin@123', 'Admin@123'),
 ('Anita Sharma', '45 Park Street, Mumbai', '9123456780', 'anita.sharma@example.com', 'Admin@456', 'Admin@456'),
 ('Vikram Singh', '78 Ring Road, Bangalore', '9988776655', 'vikram.singh@example.com', 'Admin@789', 'Admin@789'),
 ('Priya Verma', '12 MG Road, Chennai', '9871122334', 'priya.verma@example.com', 'Admin@321', 'Admin@321'),
 ('Amit Joshi', '56 Brigade Road, Pune', '9001122334', 'amit.joshi@example.com', 'Admin@654', 'Admin@654');

select * from hospital_management_system.admin;