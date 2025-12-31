package com.hospitalmanagement.project.servlet;

import com.hospitalmanagement.project.util.DBconnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/searchPatient")
public class SearchPatientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String key = "%" + req.getParameter("query") + "%";

        try (Connection con = DBconnection.getConnection()) {

            String sql =
                    "SELECT * FROM patient WHERE patientName LIKE ? OR emailId LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();
            req.setAttribute("patients", rs);

            req.getRequestDispatcher("searchPatient.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
