<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.example.ecommerce.base.repository.util.HibernateUtil" %>
<%@ page import="org.example.ecommerce.repository.ProductRepository" %>
<%@ page import="org.example.ecommerce.repository.impl.ProductRepositoryImpl" %>
<%@ page import="org.example.ecommerce.service.ProductService" %>
<%@ page import="org.example.ecommerce.service.impl.ProductServiceImpl" %>
<%@ page import="org.example.ecommerce.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.ecommerce.repository.CategoryRepository" %>
<%@ page import="org.example.ecommerce.repository.impl.CategoryRepositoryImpl" %>
<%@ page import="org.example.ecommerce.service.CategoryService" %>
<%@ page import="org.example.ecommerce.service.impl.CategoryServiceImpl" %>
<%@ page import="org.example.ecommerce.entity.Category" %>
<%@ page import="org.example.ecommerce.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <%@ include file="components/common_css_js.jsp" %>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row mt-3 mx-2">

        <%
            try {

                Session s = HibernateUtil.getSessionFactory().openSession();

                ProductRepository productRepository = new ProductRepositoryImpl(s);
                ProductService productService = new ProductServiceImpl(s, productRepository);

                List<Product> productList = new ArrayList<>(productService.findAll());
                request.setAttribute("productList", productList);

                CategoryRepository categoryRepository = new CategoryRepositoryImpl(s);
                CategoryService categoryService = new CategoryServiceImpl(s, categoryRepository);

                List<Category> categoryList = new ArrayList<>(categoryService.findAll());
                request.setAttribute("categoryList", categoryList);

            } catch (Exception e) {
                e.getStackTrace();
            }
        %>

        <%--Show Categories--%>
        <div class="col-md-2">

            <div class="list-group mt-4">
                <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
                    All Products
                </a>
                <c:forEach items="${categoryList}" var="category">
                    <a href="#" class="list-group-item list-group-item-action">${category.categoryTitle}</a>
                </c:forEach>
            </div>

        </div>

        <%--Show Products--%>
        <div class="col-md-8">

            <div class="row mt-4">

                <div class="col-md-12">

                    <div class="row row-cols-1 row-cols-md-2 g-4">
                        <c:forEach items="${productList}" var="product">
                            <div class="col">
                                <div class="card">
                                    <div class="container text-center">
                                        <img src="img/products/${product.productPhoto}"
                                             style="max-height: 150px; max-width: 100%; width: auto"
                                             class="card-img-top m2" alt="${product.productTitle}">
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${product.productTitle}</h5>
                                        <p class="card-text">
                                                ${Utils.getLessDescription(product.productDesc)}
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <button class="btn custom-bg text-white">Add to Cart</button>
                                        <button class="btn btn-outline-primary">
                                            &dollar;${product.productPrice}
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>

            </div>

        </div>

    </div>
</div>

</body>
</html>
