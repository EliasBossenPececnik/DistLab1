<%@ page import="java.util.List, se.kth.snomos.distlab1.BO.ItemInfo" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Your Shopping Cart" %></h1>
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
  <% List<ItemInfo> items = (List<ItemInfo>) request.getAttribute("shoppingCart");
    if (items != null) {
      for (ItemInfo item : items) {%>
  <tr>
    <td><%= item.getName() %></td>
    <td><%= item.getPrice() %></td>
    <td><%= item.getStock() %></td>
    <td><%= item.getCategory() %></td>
  </tr>
  <%}
  }%>
  </tbody>
</table>
<a href="index?action=all">Back to Shopping</a>
</body>
</html>