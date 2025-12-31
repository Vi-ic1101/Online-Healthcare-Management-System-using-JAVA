package com.hospitalmanagement.project.servlet;

import com.hospitalmanagement.project.util.DBconnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/pharmacistProfile")
public class PharmacistProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email =
                (String) req.getSession().getAttribute("pharmacistEmail");

        try (Connection con = DBconnection.getConnection()) {

            String sql =
                    "SELECT * FROM pharmacist WHERE emailId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                req.setAttribute("pharmacist", rs);
            }

            req.getRequestDispatcher("pharmacistProfile.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
