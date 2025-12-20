# 🏥 Hospital Management System - Java Project

## Project Overview
A complete **Hospital Management System (HMS)** built using **Java** and **MySQL** to **digitalize hospital operations** and reduce manual paperwork.  
This system efficiently manages **patients, doctors, and appointments** with ease and accuracy.

---

## Why This Project?
Hospitals handle thousands of records daily—patients, doctors, schedules. Manual management leads to:  
- ❌ High chances of errors  
- ❌ Time-consuming workflows  
- ❌ Difficulty in updating or retrieving information  

**HMS provides a centralized solution that ensures:**  
- ✅ Faster operations <BR>  
- ✅ Better accuracy  
- ✅ Improved patient care  
- ✅ Reduced paperwork  
- ✅ Elimination of appointment clashes  
- ✅ Streamlined hospital workflow  
- ✅ Increased productivity of staff and doctors  
- ✅ Secured critical hospital data in one place  

---
## Setup & Usage

### Steps to Run the Project

1. **Create the MySQL database**  
   - Open MySQL Workbench or your preferred MySQL client.  
   - Create a new database for the Hospital Management System.

2. **Import the provided SQL schema file**  
   - Use the `Import` option in MySQL Workbench or run via terminal:  
  <!-- ```sql
   source path/to/schema.sql;```-->

3. Go to Index.java
   - main class
   - on running it will open admin interface.
   - admin can login as doctor, patient and admin itself.
   - can register new doctor, patient and admin.
---

## Class Information
 1.	AddPatient.java => Page to add a new patient with personal and medical details
2.	AdminLogin.java => Admin login page to access admin dashboard
3.    Appointment.java => Page for doctors to view and manage appointments
4.    AppointmentBooking.java=> Interface to book new appointments and check for conflicts
5.    Billing.java => Handles billing and payments for patient visits
6.    ConnectionClass.java=> Database connection class using JDBC
7.    DoctorLogin.java => Doctor login page to access doctor dashboard
8.    Index.java => Main entry point of the project
9.    MainPage.java => Landing page for the system with navigation
10.	PatientLogin.java => Patient login page to access patient profile and appointments
11.	PharmacistLogin.java => Pharmacist login page for managing prescriptions and medicines
12.	ProfileAdmin.java => Admin profile page to manage users and hospital data
13.	ProfileDoctor.java => Doctor profile page to view appointments and patient info
14.	ProfilePatient.java=> Patient profile page to view appointments, prescriptions, and personal info
15.	ProfilePharmacist.java => Pharmacist profile page to manage medicines and prescriptions
16.	ProfileReceptionist.java => Receptionist profile page to manage appointments and registrations
17.	ReceptionistDashboard.java => Dashboard for receptionists to monitor appointments and patients
18.	ReceptionistLogin.java => Receptionist login page
19.	RegistrationPage.java=> Registration page for new users (patients, doctors, admin)
20.	SearchPatient.java => Page to search and retrieve patient records
21.	ViewAppointments.java => Page to view all appointments for patients, doctors, or staff


---
      
## Database Driver 
   
- mysql-connector-j-9.5.0.jar
---
## Core Features

### Patient Management
- Register/modify patient details  
- Store and track medical history  
- Retrieve patient records instantly  

### Doctor Management
- Add/update doctor details  
- Track doctor specialties  
- Manage doctor schedule availability  

### Appointment Scheduling
- Book/update/cancel appointments  
- View doctor appointment lists  
- Real-time conflict checks  

### Role-Based Access
- **Admin**  
- **Doctors**  
- **Patients**  

### Security
- Secure login system  
- passwords passwords 
- Safe database interactions via **JDBC**

---

## Database Design
### **Tables in the System**
  1=> admin<BR>
  2=> appointment<BR>
  3=> doctor<BR>
  4=> patient<BR>

### Appointment Table
| Field                 | Type        | Null | Key | Default  | Extra           |
|----------------------|------------|------|-----|---------|----------------|
| appointmentId         | int        | NO   | PRI | NULL    | auto_increment |
| doctorId              | int        | NO   | MUL | NULL    |                |
| patientId             | int        | NO   | MUL | NULL    |                |
| docAppointmentDate    | date       | NO   |     | NULL    |                |
| docAppointmentTime    | time       | NO   |     | NULL    |                |
| docAppointmentStatus  | varchar(20)| YES  |     | Pending |                |

### Patient Table
| Field                   | Type        | Null | Key | Extra           |
|------------------------|------------|------|-----|----------------|
| patientId               | int        | NO   | PRI | auto_increment |
| patientName             | varchar(100)| NO  |     |                |
| address                 | varchar(255)| YES |     |                |
| phone                   | varchar(20)| YES  |     |                |
| emailId                 | varchar(100)| YES |     |                |
| patientPassword         | varchar(50)| YES  |     |                |
| patientConfirmPassword  | varchar(50)| YES  |     |                |

### Doctor Table
| Field                   | Type        | Null | Key | Extra           |
|------------------------|------------|------|-----|----------------|
| doctorId               | int        | NO   | PRI | auto_increment |
| doctorName             | varchar(255)| NO  |     |                |
| address                | varchar(255)| YES |     |                |
| doctorDepartment       | varchar(255)| NO  |     |                |
| phone                  | varchar(255)| YES  |     |                |
| emailId                | varchar(255)| NO  | UNI |                |
| doctorPassword         | varchar(255)| YES  |     |                |
| doctorConfirmPassword  | varchar(255)| YES  |     |                |

### Admin Table
| Field                   | Type        | Null | Key | Extra           |
|------------------------|------------|------|-----|----------------|
| adminId                | int        | NO   | PRI | auto_increment |
| adminName              | varchar(255)| YES  |     |                |
| address                | varchar(255)| YES  |     |                |
| phone                  | varchar(255)| YES  |     |                |
| emailID                | varchar(255)| YES  |     |                |
| adminPassword          | varchar(255)| YES  |     |                |
| adminConfirmPassword   | varchar(255)| YES  |     |                |

---

## Technology Stack
- **Java 25**  
- **MySQL Server**  
- **Java IDE** (NetBeans, Eclipse, IntelliJ)  
- **JDBC (Java Database Connectivity)**  

---




## Create tables

Create tables for Patients, Doctors, Admins, and Appointments.

Configure JDBC connection

  **present inside connection class**
  - Update the database URL, username, and password in the Java code: <BR>
  - String url = "jdbc:mysql://localhost:3306/hospital_management_system.";<BR>
  - String username = "root";<BR>
  -String password = "12345";<BR>


## Run the project

  **From your IDE (NetBeans, Eclipse, IntelliJ)**
  Or compile and run via terminal:
  **javac Index.java
  java Index**


Start managing hospital data

You can now register patients, add doctors, schedule appointments, and access all modules.

## Key Highlights

✅ Real-time updates across all modules<BR>
✅ Intelligent appointment conflict checks<BR>
✅ Expandable console or GUI (Swing/JavaFX)<BR>
✅ Easy for both technical and non-technical staff<BR>
✅ Secure role-based access control<BR>
✅ Comprehensive data validation<BR>
✅ Relational database integrity with foreign keys<BR>



## 👥 Project Team

Meet the dedicated individuals behind this project:

| Name           | Role   | github link             |
|----------------|--------|-------------------------|
| Vidhu Kaushik  | Leader | https://github.com/Vi-ic1101 |
| Abhinav Singh  | Member |https://github.com/abhi2004-design |
| Jigyasha       | Member | https://github.com/jigyasha545 |

