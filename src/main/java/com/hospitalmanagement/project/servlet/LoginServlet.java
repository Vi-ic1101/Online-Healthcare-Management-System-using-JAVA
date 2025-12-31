package com.hospitalmanagement.project.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Dummy validation example
        if("admin".equals(username) && "admin123".equals(password)){
            // Login success, redirect to dashboard
            response.sendRedirect("index.jsp");
        } else {
            // Login failed, set error message
            request.setAttribute("error", "Invalid username or password");
            // Forward back to login page
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
