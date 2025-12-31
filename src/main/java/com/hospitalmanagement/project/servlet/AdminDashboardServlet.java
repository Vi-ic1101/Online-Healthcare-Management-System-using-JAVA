package com.hospitalmanagement.project.servlet;

import com.hospitalmanagement.project.util.DBconnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = DBconnection.getConnection()) {

            Statement stmt = con.createStatement();

            // Count total patients
            ResultSet rsPatient = stmt.executeQuery("SELECT COUNT(*) AS total FROM patient");
            rsPatient.next();
            int totalPatients = rsPatient.getInt("total");

            // Count total doctors
            ResultSet rsDoctor = stmt.executeQuery("SELECT COUNT(*) AS total FROM doctor");
            rsDoctor.next();
            int totalDoctors = rsDoctor.getInt("total");

            // Count total appointments
            ResultSet rsAppointment = stmt.executeQuery("SELECT COUNT(*) AS total FROM appointment");
            rsAppointment.next();
            int totalAppointments = rsAppointment.getInt("total");

            // Set attributes to use in JSP
            request.setAttribute("totalPatients", totalPatients);
            request.setAttribute("totalDoctors", totalDoctors);
            request.setAttribute("totalAppointments", totalAppointments);

            // Forward to adminDashboard.jsp
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
