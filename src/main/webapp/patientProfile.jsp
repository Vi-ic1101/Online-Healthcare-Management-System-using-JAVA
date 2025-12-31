<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Patient Profile</title>
    <link rel="stylesheet" href="patientProfile.css">
</head>
<body>

<div class="profile-container">
    <h2>Patient Profile</h2>

    <p><b>Name:</b> ${patientName}</p>
    <p><b>Age:</b> ${patientAge}</p>
    <p><b>Gender:</b> ${patientGender}</p>
    <p><b>Contact:</b> ${patientPhone}</p>

    <a href="bookAppointment.jsp">Book Appointment</a><br><br>
    <a href="logout">Logout</a>
</div>

</body>
</html>
