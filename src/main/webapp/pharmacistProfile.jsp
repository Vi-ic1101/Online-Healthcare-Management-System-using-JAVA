<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Pharmacist Profile</title>
    <link rel="stylesheet" href="pharmacistProfile.css">
</head>
<body>

<div class="profile-container">
    <h2>Pharmacist Profile</h2>

    <p><b>Name:</b> ${pharmacistName}</p>
    <p><b>Email:</b> ${pharmacistEmail}</p>
    <p><b>Phone:</b> ${pharmacistPhone}</p>

    <a href="logout">Logout</a>
</div>

</body>
</html>
