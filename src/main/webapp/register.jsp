<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Registration</title>
    <%@ include file="components/common_css_js.jsp" %>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row mt-2">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-header text-center fs-4">Sign Up</div>
                <div class="card-body">
                    <form action="registerUser" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Enter your username..." required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="Enter your email..." required>
                        </div>
                        <div class="mb-3">
                            <label for="pwd" class="form-label">Password</label>
                            <input type="password" class="form-control" id="pwd" name="pwd"
                                   placeholder="Enter your password..." required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="number" class="form-control" id="phone" name="phone"
                                   placeholder="Enter your phone number..." required>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <textarea class="form-control" id="address" name="address" style="height: 150px"
                                      placeholder="Enter your address..." required></textarea>
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-outline-success">Register</button>
                            <button type="reset" class="btn btn-outline-danger">Back</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p style="color: crimson">${error}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
