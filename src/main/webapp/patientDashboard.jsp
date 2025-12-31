<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>

<div class="dashboard-container">

    <h2>Welcome, Patient</h2>

    <div class="card-container">

        <a href="bookAppointment.jsp" class="card">
            <h3>Book Appointment</h3>
            <p>Schedule a new doctor appointment</p>
        </a>

        <a href="viewAppointments.jsp" class="card">
            <h3>My Appointments</h3>
            <p>View appointment status</p>
        </a>

        <a href="profile.jsp" class="card">
            <h3>My Profile</h3>
            <p>View / update personal details</p>
        </a>

        <a href="LogoutServlet" class="card logout">
            <h3>Logout</h3>
        </a>

    </div>

</div>

</body>
</html>
