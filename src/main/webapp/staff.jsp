<%@ page import="java.util.List" %>
<%@ page import="se.kth.snomos.distlab1.BO.Order" %>
<%@ page isELIgnored="false" %>

<%
    List<Order> allOrders = (List<Order>) request.getAttribute("orders");
    int orderCounter = 1;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Staff Order Management</title>
</head>
<body>

<h1>Pending Orders List</h1>

<% if (allOrders != null && !allOrders.isEmpty()) { %>
<table border="1" style="width: 50%;">
    <thead>
    <tr>
        <th>Order #</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for (Order order : allOrders) {%>
    <tr>
        <td>Order <%= orderCounter++ %></td>
        <td>
            <form action="index" method="POST" style="display: inline-block;">
                <input type="hidden" name="action" value="packOrder">
                <input type="hidden" name="orderId" value="<%= order.getOrderID() %>">
                <input type="submit" value="Mark as Packed and Complete">
            </form>
        </td>
    </tr>
    <%} %>
    </tbody>
</table>
<% } else { %>
<p>No orders found at this time.</p>
<% } %>

<div style="margin-top: 20px;">
    <a href="index?action=all"><button>Back to Homepage</button></a>
</div>

</body>
</html>
