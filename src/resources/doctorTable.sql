--
CREATE TABLE doctor (
    doctorId INT AUTO_INCREMENT PRIMARY KEY,
    doctorName VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    doctorDepartment VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    emailId VARCHAR(100) UNIQUE NOT NULL,
    doctorPassword VARCHAR(50),
    doctorConfirmPassword VARCHAR(50)
);
INSERT INTO hospital_management_system.doctor
(doctorName, address, doctorDepartment, phone, emailId, doctorPassword, doctorConfirmPassword)
VALUES
('Dr. Arjun Mehta', '12 MG Road, Mumbai, Maharashtra', 'Cardiology', '9876543210', 'arjun.mehta@hospital.com', 'pass123', 'pass123'),
('Dr. Priya Sharma', '45 Brigade Road, Bangalore, Karnataka', 'Neurology', '9876543211', 'priya.sharma@hospital.com', 'pass123', 'pass123'),
('Dr. Rohan Gupta', '78 Park Street, Kolkata, West Bengal', 'Orthopedics', '9876543212', 'rohan.gupta@hospital.com', 'pass123', 'pass123'),
('Dr. Ananya Singh', '23 MG Road, Delhi, Delhi', 'Pediatrics', '9876543213', 'ananya.singh@hospital.com', 'pass123', 'pass123'),
('Dr. Vikram Rao', '56 Anna Salai, Chennai, Tamil Nadu', 'General', '9876543214', 'vikram.rao@hospital.com', 'pass123', 'pass123'),
('Dr. Kavita Joshi', '34 FC Road, Pune, Maharashtra', 'Cardiology', '9876543215', 'kavita.joshi@hospital.com', 'pass123', 'pass123'),
('Dr. Sameer Khan', '89 Park Street, Kolkata, West Bengal', 'Neurology', '9876543216', 'sameer.khan@hospital.com', 'pass123', 'pass123');

select * from doctor;