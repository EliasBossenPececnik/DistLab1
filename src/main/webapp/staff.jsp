<%@ page import="java.util.List" %>
<%@ page import="se.kth.snomos.distlab1.BO.Order" %>
<%@ page import="se.kth.snomos.distlab1.BO.ItemInfo" %>
<%@ page import="se.kth.snomos.distlab1.BO.OrderInfo" %>
<%@ page import="se.kth.snomos.distlab1.BO.ItemHandler" %>
<%@ page import="java.util.ArrayList" %>
<%@ page isELIgnored="false" %>

<%
    List<OrderInfo> orderInfo = (List<OrderInfo>) request.getAttribute("orderDetails");
    List<Order> allOrders = (List<Order>) request.getAttribute("orders");
    int orderCounter = 1;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Staff Order Management</title>
</head>
<body>

<% if (orderInfo != null && !orderInfo.isEmpty()) {
    int currentOrderId = orderInfo.get(0).getOrderID();

    List<ItemInfo> itemsInOrder = new ArrayList<>();
    for (OrderInfo o : orderInfo){
        itemsInOrder.add(ItemHandler.getItemByID(o.getItemID()));
    }
%>
<h1>Details for Order #<%= currentOrderId %></h1>
<table border="1" style="width: 80%;">
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <% if (!itemsInOrder.isEmpty()) {
        for (ItemInfo item : itemsInOrder) { %>
    <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getPrice() %></td>
        <td><%= item.getCategory() %></td>
    </tr>
    <% }} else { %>
    <tr><td colspan="3">No items found for this order.</td></tr>
    <% } %>
    </tbody>
</table>

<div style="margin-top: 20px;">
    <form action="index" method="POST" style="display: inline-block;">
        <input type="hidden" name="action" value="packOrder">
        <input type="hidden" name="orderId" value="<%= currentOrderId %>">
        <input type="submit" value="Mark as Packed and Complete">
    </form>

    <a href="index?action=staff" style="margin-left: 20px;">
        <button>← Back to Order List</button>
    </a>
</div>

<% } else {

%>
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
            <a href="index?action=viewOrderDetails&orderId=<%= order.getOrderID() %>">
                <button>View Order</button>
            </a>
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

<% } %>

</body>
</html>