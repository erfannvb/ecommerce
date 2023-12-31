<%@ page import="org.example.ecommerce.entity.User" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first to access the checkout page.");
        response.sendRedirect("/login.jsp");
        return;
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Checkout</title>
    <%@ include file="components/common_css_js.jsp" %>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <div class="row mt-5">

        <div class="col-md-6">

            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Your selected items</h3>
                    <div class="cart-body"></div>
                </div>
            </div>

        </div>

        <div class="col-md-6">

            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Your order details</h3>
                    <form action="#">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="Enter your email..." value="<%= currentUser.getUserEmail() %>">
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Enter your name..." value="<%= currentUser.getUsername() %>">
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="number" class="form-control" id="phone" name="phone"
                                   placeholder="Enter your phone number..." value="<%= currentUser.getUserPhone() %>">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <textarea style="height: 150px" class="form-control" id="address" name="address"
                                      placeholder="Enter your address...">
                                <%= currentUser.getUserAddress() %>
                            </textarea>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Order Now</button>
                            <button class="btn btn-outline-primary">Continue Shopping</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    </div>
</div>

<jsp:include page="components/common_modals.jsp"/>

</body>
</html>
