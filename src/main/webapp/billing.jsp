<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Billing</title>
    <link rel="stylesheet" href="billing.css">
</head>
<body>

<div class="billing-container">
    <h2>Patient Billing</h2>

    <form method="post" action="BillingServlet">

        <div class="row">
            <label>Patient Name</label>
            <input type="text" name="patientName"
                   value="<%= request.getParameter("patientName") != null ? request.getParameter("patientName") : "" %>">
        </div>

        <div class="row">
            <label>Patient Email</label>
            <input type="email" name="patientEmail"
                   value="<%= request.getParameter("patientEmail") != null ? request.getParameter("patientEmail") : "" %>">
        </div>

        <div class="btn-row">
            <button type="submit" name="action" value="fetch">Fetch Patient</button>
        </div>

        <hr>

        <div class="row">
            <label>Doctor Charge</label>
            <input type="number" name="doctorCharge" value="0">
        </div>

        <div class="row">
            <label>Room Charge</label>
            <input type="number" name="roomCharge" value="0">
        </div>

        <div class="row">
            <label>Medicine Charge</label>
            <input type="number" name="medicineCharge" value="0">
        </div>

        <div class="row">
            <label>Other Charge</label>
            <input type="number" name="otherCharge" value="0">
        </div>

        <div class="row total">
            <label>Total Amount</label>
            <input type="text" readonly
                   value="<%= request.getAttribute("total") != null ? request.getAttribute("total") : "" %>">
        </div>

        <div class="btn-row">
            <button type="submit" name="action" value="calculate">Calculate</button>
            <button type="submit" name="action" value="save">Save</button>
            <button type="reset">Clear</button>
            <a href="pharmacistProfile.jsp" class="back-btn">Back</a>
        </div>

    </form>

    <% if (request.getAttribute("message") != null) { %>
        <p class="message"><%= request.getAttribute("message") %></p>
    <% } %>

</div>

</body>
</html>
