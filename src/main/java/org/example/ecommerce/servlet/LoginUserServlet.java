package org.example.ecommerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ecommerce.entity.User;

import java.io.IOException;

@WebServlet(name = "LoginUserServlet", urlPatterns = "/loginUser")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        User currentUser = (User) httpSession.getAttribute("currentUser");

        // Check if the logged-in user is amin or normal user
        if (currentUser.getUserType().equals("admin")) {
            resp.sendRedirect("/admin.jsp");
        } else if (currentUser.getUserType().equals("normal")) {
            resp.sendRedirect("/normalUser.jsp");
        } else {
            resp.setContentType("text/html");
            resp.getWriter().println("<h2>Couldn't identify the user type</h2>");
        }

    }
}
