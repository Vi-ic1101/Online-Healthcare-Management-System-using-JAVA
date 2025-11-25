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
- View doctor-wise appointment lists  
- Real-time conflict checks  

### Role-Based Access
- **Admin**  
- **Doctors**  
- **Patients**  

### Security
- Secure login system  
- Encrypted passwords  
- Safe database interactions via **JDBC**

---

## Database Design

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
| doctorName             | varchar(100)| NO  |     |                |
| address                | varchar(255)| YES |     |                |
| doctorDepartment       | varchar(100)| NO  |     |                |
| phone                  | varchar(20)| YES  |     |                |
| emailId                | varchar(100)| NO  | UNI |                |
| doctorPassword         | varchar(50)| YES  |     |                |
| doctorConfirmPassword  | varchar(50)| YES  |     |                |

### Admin Table
| Field                   | Type        | Null | Key | Extra           |
|------------------------|------------|------|-----|----------------|
| adminId                | int        | NO   | PRI | auto_increment |
| adminName              | varchar(50)| YES  |     |                |
| address                | varchar(10)| YES  |     |                |
| phone                  | varchar(15)| YES  |     |                |
| emailID                | varchar(30)| YES  |     |                |
| adminPassword          | varchar(20)| YES  |     |                |
| adminConfirmPassword   | varchar(20)| YES  |     |                |

---

## Technology Stack
- **Java 25**  
- **MySQL Server**  
- **Java IDE** (NetBeans, Eclipse, IntelliJ)  
- **JDBC (Java Database Connectivity)**  

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


## Create tables

Create tables for Patients, Doctors, Admins, and Appointments.

Configure JDBC connection

  **present inside connection class**
  - Update the database URL, username, and password in the Java code: <BR>
  - String url = "jdbc:mysql://localhost:3306/hms_database";<BR>
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

## **Tables in the System**
  1=> admin<BR>
  2=> appointment<BR>
  3=> doctor<BR>
  4=> patient<BR>
