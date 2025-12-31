<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Appointments</title>
    <link rel="stylesheet" href="viewAppointments.css">
</head>
<body>

<div class="appointment-container">
    <h2>Manage Appointments</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Patient</th>
            <th>Doctor</th>
            <th>Department</th>
            <th>Date</th>
            <th>Time</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hospital_management_system",
                        "root", "12345"
                );

                String sql = "SELECT * FROM appointment";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("appointmentId") %></td>
            <td><%= rs.getString("patientName") %></td>
            <td><%= rs.getString("doctorName") %></td>
            <td><%= rs.getString("doctorDepartment") %></td>
            <td><%= rs.getString("docAppointmentDate") %></td>
            <td><%= rs.getString("docAppointmentTime") %></td>
            <td><%= rs.getString("docAppointmentStatus") %></td>
            <td>
                <form method="post" action="viewAppointments.jsp" class="inline">
                    <input type="hidden" name="id" value="<%= rs.getInt("appointmentId") %>">
                    <input type="hidden" name="status" value="Approved">
                    <button class="approve">Approve</button>
                </form>

                <form method="post" action="viewAppointments.jsp" class="inline">
                    <input type="hidden" name="id" value="<%= rs.getInt("appointmentId") %>">
                    <input type="hidden" name="status" value="Cancelled">
                    <button class="cancel">Cancel</button>
                </form>
            </td>
        </tr>
        <%
                }
                con.close();
            } catch (Exception e) {
                out.println("<tr><td colspan='8'>Error loading appointments</td></tr>");
            }
        %>
    </table>

    <div class="actions">
        <a href="viewAppointments.jsp">🔄 Refresh</a>
        <a href="adminDashboard.jsp">⬅ Back</a>
    </div>
</div>

<%
    // ---------- UPDATE STATUS ----------
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root", "12345"
            );

            String update =
                    "UPDATE appointment SET docAppointmentStatus=? WHERE appointmentId=?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

            con.close();
            response.sendRedirect("viewAppointments.jsp");

        } catch (Exception e) {
            out.println("Update failed");
        }
    }
%>

</body>
</html>
