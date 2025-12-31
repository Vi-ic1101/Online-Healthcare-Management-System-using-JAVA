<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register | Healthcare Management System</title>

    <!-- CSS -->
    <link rel="stylesheet" href="css/register.css">

    <!-- JavaScript for Role Handling -->
    <script>
        function toggleDept(role) {
            const deptDiv = document.getElementById("dept");
            if (role === "Doctor") {
                deptDiv.style.display = "block";
            } else {
                deptDiv.style.display = "none";
            }
        }
    </script>
</head>

<body>

<div class="register-container">
    <h2>User Registration</h2>

    <!-- Error Message -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="error-msg"><%= error %></div>
    <%
        }
    %>

    <!-- Success Message -->
    <%
        String success = (String) request.getAttribute("success");
        if (success != null) {
    %>
        <div class="success-msg"><%= success %></div>
    <%
        }
    %>

    <form action="RegisterServlet" method="post">

        <!-- Role -->
        <div class="form-group">
            <label>Role</label>
            <select name="role" required onchange="toggleDept(this.value)">
                <option value="">Select Role</option>
                <option value="Doctor">Doctor</option>
                <option value="Patient">Patient</option>
                <option value="Admin">Admin</option>
                <option value="Pharmacist">Pharmacist</option>
                <option value="Receptionist">Receptionist</option>
            </select>
        </div>

        <!-- Name -->
        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" required>
        </div>

        <!-- Email -->
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" required>
        </div>

        <!-- Phone -->
        <div class="form-group">
            <label>Phone</label>
            <input type="text" name="phone" required>
        </div>

        <!-- Address -->
        <div class="form-group">
            <label>Address</label>
            <input type="text" name="address" required>
        </div>

        <!-- Department (Doctor only) -->
        <div class="form-group" id="dept">
            <label>Department</label>
            <input type="text" name="dept">
        </div>

        <!-- Password -->
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>

        <!-- Confirm Password -->
        <div class="form-group">
            <label>Confirm Password</label>
            <input type="password" name="confirmPassword" required>
        </div>

        <!-- Submit -->
        <button type="submit" class="btn-register">Register</button>

    </form>

    <!-- Login link -->
    <div class="login-link">
        <a href="login.jsp">Already have an account? Login</a>
    </div>

</div>

</body>
</html>
