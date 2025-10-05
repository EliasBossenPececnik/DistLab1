<%--
  Created by IntelliJ IDEA.
  User: Johan
  Date: 2025-10-05
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Warehouse Login</h1>
<c:if test="{not empty error}">
    <p>${error}</p>
</c:if>
<form action="Controller" method="POST">
    <input type="hidden" name="action" value="login">
    <label for="username">Username:</label>
    <input type="text" id="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
