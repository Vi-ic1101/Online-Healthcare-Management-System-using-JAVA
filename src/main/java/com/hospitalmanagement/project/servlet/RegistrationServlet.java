package com.hospitalmanagement.project.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.hospitalmanagement.project.dao.UserDAO;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String department = request.getParameter("department");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validation
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || address.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty() || role.equals("Select")) {

            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        boolean status = UserDAO.registerUser(
                role, name, email, phone, address, department, password, confirmPassword
        );

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
