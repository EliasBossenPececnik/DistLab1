<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Warehouse" %></h1>
<a href="index">Go to Login Page</a>
<h2>It's not retail, it's the Warehouse</h2>
<br/>
<table border="1">
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.stock}</td>
            <td>${item.category}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>