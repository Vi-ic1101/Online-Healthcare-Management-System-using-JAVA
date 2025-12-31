<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
    <link rel="stylesheet" href="form.css">
</head>
<body>

<div class="form-container">

    <h2>Book Doctor Appointment</h2>

    <form action="BookAppointmentServlet" method="post">

        <label>Patient Name</label>
        <input type="text" name="patientName" required>

        <label>Doctor Name</label>
        <input type="text" name="doctorName" required>

        <label>Department</label>
        <select name="department" required>
            <option value="">Select</option>
            <option>Cardiology</option>
            <option>Neurology</option>
            <option>Orthopedics</option>
            <option>Pediatrics</option>
            <option>General</option>
        </select>

        <label>Appointment Date</label>
        <input type="date" name="appointmentDate" required>

        <label>Appointment Time</label>
        <input type="time" name="appointmentTime" required>

        <button type="submit">Book Appointment</button>

    </form>

    <a href="patientDashboard.jsp" class="back-link">← Back to Dashboard</a>

</div>

</body>
</html>
