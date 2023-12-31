<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            String category = request.getParameter("category");

            try {

                Session s = HibernateUtil.getSessionFactory().openSession();

                ProductRepository productRepository = new ProductRepositoryImpl(s);
                ProductService productService = new ProductServiceImpl(s, productRepository);

                List<Product> productList;
                if (category == null || category.trim().equals("all")) {
                    productList = new ArrayList<>(productService.findAll());
                } else {
                    long categoryId = Long.parseLong(category.trim());
                    productList = productService.getAllProductByCategoryId(categoryId);
                }

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
                <a href="index.jsp?category=all"
                   class="list-group-item list-group-item-action active" aria-current="true">
                    All Products
                </a>
                <c:forEach items="${categoryList}" var="category">
                    <a href="index.jsp?category=${category.id}"
                       class="list-group-item list-group-item-action">${category.categoryTitle}</a>
                </c:forEach>
            </div>

        </div>

        <%--Show Products--%>
        <div class="col-md-10">

            <div class="row mt-4">

                <div class="col-md-12">

                    <div class="row row-cols-1 row-cols-md-2 g-4">
                        <c:forEach items="${productList}" var="product">
                            <div class="col">
                                <div class="card product-card">
                                    <div class="container text-center">
                                        <img src="img/products/${product.productPhoto}"
                                             style="max-height: 150px; max-width: 100%; width: auto"
                                             class="card-img-top m-2" alt="${product.productTitle}">
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${product.productTitle}</h5>
                                        <p class="card-text">
                                                ${Utils.getLessDescription(product.productDesc)}
                                        </p>
                                    </div>
                                    <div class="card-footer text-center">
                                        <button class="btn custom-bg text-white"
                                                onclick="addToCart(${product.id}, '${product.productTitle}', ${Utils.getPriceAfterApplyingDiscount(product.productPrice, product.productDiscount)})">
                                            Add to Cart
                                        </button>
                                        <button class="btn btn-outline-primary">
                                            <fmt:formatNumber type="currency"
                                                              value="${Utils.getPriceAfterApplyingDiscount(product.productPrice, product.productDiscount)}"/>
                                            <span class="text-secondary discount-label"><fmt:formatNumber
                                                    type="currency"
                                                    value="${product.productPrice}"/> ${product.productDiscount}% off</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${productList.size() == 0}">
                            <h3>No item in this category.</h3>
                        </c:if>
                    </div>

                </div>

            </div>

        </div>

    </div>
</div>

<jsp:include page="components/common_modals.jsp"/>

</body>
</html>
