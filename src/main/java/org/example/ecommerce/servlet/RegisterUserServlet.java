package org.example.ecommerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.base.repository.util.HibernateUtil;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.example.ecommerce.repository.impl.UserRepositoryImpl;
import org.example.ecommerce.service.UserService;
import org.example.ecommerce.service.impl.UserServiceImpl;
import org.hibernate.Session;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterUserServlet", urlPatterns = "/registerUser")
public class RegisterUserServlet extends HttpServlet {

    private Session session;
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        session = HibernateUtil.getSessionFactory().openSession();
        userRepository = new UserRepositoryImpl(session);
        userService = new UserServiceImpl(session, userRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            User user = new User(username, email, password, phone, address, "Normal");
            userService.addUser(user);

            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>User Added Successfully!!!</h2>");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
