package org.example.ecommerce.filter;

import jakarta.persistence.NoResultException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
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
import java.util.Optional;

@WebFilter(filterName = "LoginUserFilter", servletNames = "LoginUserServlet", urlPatterns = "/loginUser")
public class LoginUserFilter implements Filter {

    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final UserRepository userRepository = new UserRepositoryImpl(session);
    private final UserService userService = new UserServiceImpl(session, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();

        StringBuilder error = new StringBuilder();

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Optional<User> userOptional = userService.findUserByEmailAndPassword(email, password);
            if (userOptional.isPresent()) {
                User currentUser = userOptional.get();
                httpSession.setAttribute("currentUser", currentUser);
                filterChain.doFilter(request, response);
            }

        } catch (NoResultException e) {
            error.append("Invalid Username/Password. Please Try Again!");
            httpSession.setAttribute("error", error.toString());
            response.sendRedirect("/login.jsp");
        }

    }
}
