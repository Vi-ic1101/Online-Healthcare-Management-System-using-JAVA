CREATE TABLE appointment (
    appointmentId INT AUTO_INCREMENT PRIMARY KEY,
    doctorId INT NOT NULL,
    patientId INT NOT NULL,
    docAppointmentDate DATE NOT NULL,
    docAppointmentTime TIME NOT NULL,
    docAppointmentStatus VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (doctorId) REFERENCES doctor(doctorId) ON DELETE CASCADE,
    FOREIGN KEY (patientId) REFERENCES patient(patientId) ON DELETE CASCADE
);
select * from appointment;