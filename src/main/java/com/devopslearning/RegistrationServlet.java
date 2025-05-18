package com.devopslearning;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String pass = req.getParameter("pass");

        res.setContentType("text/html");
        res.getWriter().println("<h2>Registration Successful!</h2>");
        res.getWriter().println("<p>Name: " + name + "</p>");
        res.getWriter().println("<p>Email: " + email + "</p>");
        res.getWriter().println("<p>Mobile: " + mobile + "</p>");
    }
}