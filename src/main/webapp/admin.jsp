<%@ page import="se.kth.snomos.distlab1.BO.ItemInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<h1>Admin User Management</h1>
<p>Use this form to promote or change a user's role.</p>

<form action="index" method="POST">
    <input type="hidden" name="action" value="promoteUser">
    <label for="targetUsername">Username to Promote:</label><br>
    <input type="text" id="targetUsername" name="targetUsername" required><br><br>
    <label for="newRole">Select New Role:</label><br>
    <select id="newRole" name="newRole" required>
        <option value="">-- Select Role --</option>
        <option value="1">Admin</option>  <option value="2">Staff</option>  <option value="3">Customer</option> </select><br><br>
    <input type="submit" value="Change User Role">
</form>

<h2>Add / Edit Item Form</h2>
<p>Leave "Item Name" blank to create a new item.</p>

<form action="index" method="POST">
    <input type="hidden" name="action" value="itemUpdate">
    <label for="itemName">Item Name (Unique):</label><br>
    <input type="text" id="itemName" name="itemName" required><br><br>
    <label for="price">Price:</label><br>
    <input type="number" step="0.01" id="price" name="price" required><br><br>
    <label for="stock">Stock Quantity:</label><br>
    <input type="number" id="stock" name="stock" required><br><br>
    <label for="category">Category:</label><br>
    <input type="text" id="category" name="category" required><br><br>
    <input type="submit" value="Save/Update Item">
</form>

<hr>

<h2>Existing Items (For Editing)</h2>
<% List<ItemInfo> items = (List<ItemInfo>) request.getAttribute("items");
    if (items != null) { %>
<table border="1">
    <thead>
    <tr><th>Name</th><th>Price</th><th>Stock</th><th>Action</th></tr>
    </thead>
    <tbody>
    <% for (ItemInfo item : items) {%>
    <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getPrice() %></td>
        <td><%= item.getStock() %></td>
        <td>
            <a href="index?action=admin&editName=<%= item.getName() %>&editPrice=<%= item.getPrice() %>&editStock=<%= item.getStock() %>&editCategory=<%= item.getCategory() %>">
                Edit
            </a>
        </td>
    </tr>
    <%} %>
    </tbody>
</table>
<% } else { %>
<p>No items found.</p>
<% } %>

<hr>
<a href="index?action=all">Back to Homepage</a>