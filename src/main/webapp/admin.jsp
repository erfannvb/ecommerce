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

<h1>This is the admin page</h1>

</body>
</html>
