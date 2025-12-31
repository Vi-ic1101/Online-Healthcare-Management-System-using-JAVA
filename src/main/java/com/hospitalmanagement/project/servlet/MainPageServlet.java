package com.hospitalmanagement.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Controller for main page navigation
 */
@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            response.sendRedirect("login.jsp");
        } else if ("register".equals(action)) {
            response.sendRedirect("register.jsp");
        }
    }
}
