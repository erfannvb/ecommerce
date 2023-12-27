package org.example.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
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
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "RegisterUserFilter", servletNames = "RegisterUserServlet", urlPatterns = "/registerUser")
public class RegisterUserFilter implements Filter {

    private static final String ERROR = "error";
    private static final String REGISTER_URL = "/register.jsp";

    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final UserRepository userRepository = new UserRepositoryImpl(session);
    private final UserService userService = new UserServiceImpl(session, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        boolean usernameExists = false;
        boolean emailExists = false;

        HttpSession httpSession = request.getSession();

        StringBuilder error = new StringBuilder();

        List<User> userList = new ArrayList<>(userService.findAll());
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                usernameExists = true;
                break;
            } else if (user.getUserEmail().equals(email)) {
                emailExists = true;
                break;
            }
        }

        if (usernameExists) {
            error.append("User with this username already exists!");
            httpSession.setAttribute(ERROR, error.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTER_URL);
            dispatcher.include(request, response);
        } else if (emailExists) {
            error.append("Email already taken!");
            httpSession.setAttribute(ERROR, error.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTER_URL);
            dispatcher.include(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
