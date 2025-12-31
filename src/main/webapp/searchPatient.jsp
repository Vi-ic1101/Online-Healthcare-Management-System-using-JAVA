<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Patient</title>
    <link rel="stylesheet" href="searchPatient.css">
</head>
<body>

<div class="container">
    <h2>Search Patient</h2>

    <form action="SearchPatientServlet" method="get" class="search-box">
        <input type="text" name="keyword"
               placeholder="Search by Name or Email"
               value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>"
               required>
        <button type="submit">Search</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Patient Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <%
                java.util.List<java.util.Map<String,String>> patients =
                        (java.util.List<java.util.Map<String,String>>) request.getAttribute("patients");

                if (patients != null && !patients.isEmpty()) {
                    for (java.util.Map<String,String> p : patients) {
            %>
            <tr>
                <td><%= p.get("id") %></td>
                <td><%= p.get("name") %></td>
                <td><%= p.get("email") %></td>
                <td><%= p.get("phone") %></td>
                <td><%= p.get("address") %></td>
            </tr>
            <%
                    }
                } else if (request.getParameter("keyword") != null) {
            %>
            <tr>
                <td colspan="5" class="no-data">No patient found</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
