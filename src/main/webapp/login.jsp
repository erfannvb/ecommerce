<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <%@ include file="components/common_css_js.jsp" %>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card mt-3">
                <jsp:include page="components/error_message.jsp"/>
                <div class="card-header text-center fs-4 custom-bg text-white"><h3>Login</h3></div>
                <div class="card-body">
                    <form action="loginUser" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="Enter email..." required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter password..." required>
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                            <button type="reset" class="btn btn-danger">Back</button>
                        </div>
                        <div class="mt-3 text-center">
                            <p>Not a member? <a href="register.jsp">Sign Up</a></p>
                        </div>
                    </form>
                </div>
                <div class="card-footer">

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
