package org.example.ecommerce.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ecommerce.base.repository.util.HibernateUtil;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.example.ecommerce.repository.impl.UserRepositoryImpl;
import org.example.ecommerce.service.UserService;
import org.example.ecommerce.service.impl.UserServiceImpl;
import org.hibernate.Session;

import java.io.IOException;

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

            HttpSession httpSession = req.getSession();

            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            if (username == null || username.isBlank() || email == null || email.isBlank() ||
                    password == null || password.isBlank() || phone == null || phone.isBlank() ||
                    address == null || address.isBlank()) {
                httpSession.setAttribute("error", "You cannot enter empty value.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
                dispatcher.include(req, resp);
                return;
            }

            User user = new User(username, email, password, phone, address, "Normal");
            userService.addUser(user);


            httpSession.setAttribute("message", username + " added successfully!");
            resp.sendRedirect("/register.jsp");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
