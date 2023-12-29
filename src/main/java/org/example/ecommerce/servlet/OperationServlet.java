package org.example.ecommerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.ecommerce.base.repository.util.HibernateUtil;
import org.example.ecommerce.entity.Category;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.repository.CategoryRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.example.ecommerce.repository.impl.CategoryRepositoryImpl;
import org.example.ecommerce.repository.impl.ProductRepositoryImpl;
import org.example.ecommerce.service.CategoryService;
import org.example.ecommerce.service.ProductService;
import org.example.ecommerce.service.impl.CategoryServiceImpl;
import org.example.ecommerce.service.impl.ProductServiceImpl;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "OperationServlet", urlPatterns = "/operation")
@MultipartConfig
public class OperationServlet extends HttpServlet {

    private Session session;

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    private ProductRepository productRepository;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        session = HibernateUtil.getSessionFactory().openSession();

        categoryRepository = new CategoryRepositoryImpl(session);
        categoryService = new CategoryServiceImpl(session, categoryRepository);

        productRepository = new ProductRepositoryImpl(session);
        productService = new ProductServiceImpl(session, productRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String operation = req.getParameter("operation");

        if (operation.trim().equals("addCategory")) {

            String categoryTitle = req.getParameter("categoryTitle");
            String categoryDesc = req.getParameter("categoryDesc");

            Category category = new Category();
            category.setCategoryTitle(categoryTitle);
            category.setCategoryDesc(categoryDesc);

            categoryService.save(category);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("message", "Category Added Successfully!");
            resp.sendRedirect("/admin.jsp");

        } else if (operation.trim().equals("addProduct")) {

            String productTitle = req.getParameter("productTitle");
            String productDesc = req.getParameter("productDesc");
            Part productPhoto = req.getPart("productPhoto");
            double productPrice = Double.parseDouble(req.getParameter("productPrice"));
            double productDiscount = Double.parseDouble(req.getParameter("productDiscount"));
            int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
            String category = req.getParameter("category");

            Product product = new Product();

            product.setProductTitle(productTitle);
            product.setProductDesc(productDesc);
            product.setProductPhoto(productPhoto.getSubmittedFileName());
            product.setProductPrice(productPrice);
            product.setProductDiscount(productDiscount);
            product.setProductQuantity(productQuantity);

            Category categoryByTitle = categoryService.getCategoryByTitle(category);
            product.setCategory(categoryByTitle);

            productService.addProduct(product);
            resp.setContentType("text/html");
            resp.getWriter().println("<h2>Product Saved Successfully!</h2>");

        }

    }
}
