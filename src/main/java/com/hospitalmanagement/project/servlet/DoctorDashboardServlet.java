package com.hospitalmanagement.project.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/doctorDashboard")
public class DoctorDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("doctorEmail") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        RequestDispatcher rd =
                req.getRequestDispatcher("doctorDashboard.jsp");
        rd.forward(req, resp);
    }
}
