package com.hospitalmanagement.project.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/appointmentBooking")
public class AppointmentBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("appointmentBooking.jsp")
                .forward(req, resp);
    }
}
