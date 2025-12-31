<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Healthcare Management System</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<div class="container">

    <h1 class="title1">HealthCare Management System</h1>
    <h2 class="title2">Project using JAVA</h2>
    <h3 class="title3">Administrator Access Page</h3>

    <form action="IndexServlet" method="post">
        <button name="role" value="Doctor">Doctor</button>
        <button name="role" value="Patient">Patient</button>
        <button name="role" value="Admin">Admin</button>
        <button name="role" value="Pharmacist">Pharmacist</button>
        <button name="role" value="Receptionist">Receptionist</button>

        <button class="back" name="role" value="Back">Back</button>
    </form>

</div>

</body>
</html>
