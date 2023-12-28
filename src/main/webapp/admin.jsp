<%@ page import="org.example.ecommerce.entity.User" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    } else {
        if (currentUser.getUserType().equals("normal")) {
            session.setAttribute("error", "You are not admin! Access Denied.");
            response.sendRedirect("/login.jsp");
            return;
        }
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Panel</title>
    <%@ include file="components/common_css_js.jsp" %>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container admin">

    <div class="container-fluid mt-3">
        <jsp:include page="components/ok_message.jsp"/>
    </div>

    <div class="row mt-3">

        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/users.png" alt="Users" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <h2>512</h2>
                    <h2 class="text-muted">Users</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/list.png" alt="Users" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <h2>512</h2>
                    <h2 class="text-muted">Categories</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/product.png" alt="Users" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <h2>512</h2>
                    <h2 class="text-muted">Products</h2>
                </div>
            </div>
        </div>

    </div>

    <div class="row mt-3">

        <div class="col-md-6">
            <div class="card" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/keys.png" alt="Users" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <h2 class="text-muted">Add Category</h2>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/plus.png" alt="Users" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <h2 class="text-muted">Add Product</h2>
                </div>
            </div>
        </div>

    </div>
</div>

<%--Add Category Modal--%>

<!-- Modal -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Category Details</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body p-3">
                <form action="operation" method="post">
                    <input type="hidden" name="operation" value="addCategory">
                    <div class="mb-3">
                        <label for="categoryTitle" class="form-label">Category Title</label>
                        <input type="text" class="form-control" id="categoryTitle" name="categoryTitle"
                               placeholder="Enter the category title..." required>
                    </div>
                    <div class="mb-3">
                        <label for="categoryDesc" class="form-label">Category Description</label>
                        <textarea style="height: 150px" class="form-control" id="categoryDesc" name="categoryDesc"
                                  placeholder="Enter the category description..." required></textarea>
                    </div>
                    <div class="container text-center">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Add Category</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--End of Add Category Modal--%>

</body>
</html>
