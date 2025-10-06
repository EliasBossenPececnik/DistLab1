<%@ page import="java.util.List, se.kth.snomos.distlab1.BO.ItemInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Warehouse" %></h1>
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
    <% List<ItemInfo> items = (List<ItemInfo>) request.getAttribute("items");
    if (items != null) {
        for (ItemInfo item : items) {%>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getStock() %></td>
                <td><%= item.getCategory() %></td>
                <td>
                    <form action="index" method="POST">
                        <input type="hidden" name="action" value="addItem">
                        <input type="hidden" name="itemName" value="<%= item.getName() %>">
                        <input type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
    <%}
    }%>
    </tbody>
</table>
<a href="index?action=shoppingCart">View your cart</a>
<% String userRole = (String) session.getAttribute("userRole");
    boolean isAdmin = "admin".equals(userRole);
    String disabledButton = isAdmin ? "" : "disabled"; %>
<div style="margin-top: 20px;">
    <a href="index?action=adminAction">
        <button <%= disabledButton %>>
            Admin Tools
        </button>
    </a>
    <%--
    <form action="index" method="POST">
        <input type="hidden" name="action" value="adminSubmit">
        <input type="submit" value="Admin Action" <%= disabledAttribute %>>
    </form>
    --%>
</div>
</body>
</html>