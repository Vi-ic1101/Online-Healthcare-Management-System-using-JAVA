package com.hospitalmanagement.project.servlet;

import com.hospitalmanagement.project.util.DBconnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/doctorAppointments")
public class DoctorAppointmentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        int doctorId = (int) session.getAttribute("doctorId");

        try (Connection con = DBconnection.getConnection()) {

            String sql =
                    "SELECT * FROM appointment WHERE doctorId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            req.setAttribute("appointments", rs);

            RequestDispatcher rd =
                    req.getRequestDispatcher("doctorAppointments.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
