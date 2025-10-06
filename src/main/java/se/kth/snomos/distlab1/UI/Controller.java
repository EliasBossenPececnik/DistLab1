package se.kth.snomos.distlab1.UI;

import java.io.*;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import se.kth.snomos.distlab1.BO.*;

@WebServlet(name = "Controller", value = "/index")
public class Controller extends HttpServlet {
    private String message;
    private ShoppingCartHandler shoppingCartHandler;

    public void init() {
        message = "Hello World!";
        shoppingCartHandler = new ShoppingCartHandler();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String nextPage = "";

        if (action == null) {
            action = "all";
        }

        switch (action) {
            case "all" :
                List<ItemInfo> items = ItemHandler.getAllItems();
                request.setAttribute("items", items);
                nextPage = "index.jsp";
                break;
            case "shoppingCart" :
                request.setAttribute("shoppingCart", shoppingCartHandler.getCart().getItems());
                nextPage = "shoppingcart.jsp";
                break;
            case "staff" :
                request.setAttribute("orders", OrderHandler.getAllOrders());
                nextPage = "staff.jsp";
                break;
            case "viewOrderDetails" :
                String orderIdString = request.getParameter("orderId");
                request.setAttribute("orders", OrderHandler.getAllOrders());
                if (orderIdString != null) {
                    try {
                        int orderId = Integer.parseInt(orderIdString);
                        List<OrderInfo> orderInfo = OrderInfo.getOrder(orderId);
                        request.setAttribute("orderInfo", orderInfo);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid Order ID provided for details view.");
                    }
                }
                nextPage = "staff.jsp";
                break;
            case "admin" :
                request.setAttribute("items", ItemHandler.getAllItems());
                nextPage = "admin.jsp";
                break;
            case "login" :
                nextPage = "login.jsp";
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "login" :
                handleLogin(request, response);
                break;
            case "addItem" :
                handleAddItem(request, response);
                break;
            case "placeOrder" :
                handlePlaceOrder(request, response);
                break;
            case "promoteUser" :
                handlePromoteUser(request, response);
                break;
            case "itemUpdate" :
                handleItemUpdate(request, response);
                break;
            case "packOrder" :
                handlePackOrder(request, response);
                break;
        }
    }

    public void destroy() {

    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int loginSuccess = UserHandler.logIn(username, password);
        if (loginSuccess == 3) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/index?action=all");
        } else if (loginSuccess == 2) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userRole", "staff");
            response.sendRedirect(request.getContextPath() + "/index?action=all");
        } else if (loginSuccess == 1) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userRole", "admin");
            response.sendRedirect(request.getContextPath() + "/index?action=all");
        } else {
            response.sendRedirect(request.getContextPath() + "/index?action=login");
        }
    }

    private void handleAddItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String item = request.getParameter("itemName");

        if (item != null && !item.isEmpty()) {
            try {
                ItemInfo itemToAdd = ItemHandler.getItemByName(item);

                if (itemToAdd.getName() != null) {
                    shoppingCartHandler.addItem(itemToAdd);
                }

            } catch (NumberFormatException e) {
                System.err.println("Invalid item ID format: " + item);
            }
        }
        response.sendRedirect(request.getContextPath() + "/index?action=all");
    }

    private void handlePlaceOrder(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (!shoppingCartHandler.getCart().getCartItems().isEmpty()) {
            OrderHandler.placeOrder(shoppingCartHandler.getCart(), (String) request.getSession().getAttribute("username"));
            shoppingCartHandler.clearCart();
            response.sendRedirect(request.getContextPath() + "/index?action=all");
        } else {
            response.sendRedirect(request.getContextPath() + "/index?action=shoppingCart");
        }
    }

    private void handlePromoteUser(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String targetUsername = request.getParameter("targetUsername");
        String newRole = request.getParameter("newRole");

        if (targetUsername != null && newRole != null) {
            UserHandler.promoteUser(targetUsername, newRole);
        }

        response.sendRedirect(request.getContextPath() + "/index?action=admin");
    }

    private void handleItemUpdate(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("itemName");
        String priceString = request.getParameter("price");
        String stockString = request.getParameter("stock");
        String category = request.getParameter("category");

        try {
            double price = Double.parseDouble(priceString);
            int stock = Integer.parseInt(stockString);

            List<ItemInfo> existingItems = ItemHandler.getAllItems();
            boolean found = false;
            for (ItemInfo i : existingItems) {
                if (i.getName().equals(name)) {
                    found = true;
                }
            }
            if (found) {
                ItemHandler.updateItem(name, price, stock, category);
            } else {
                ItemHandler.addItem(name, price, stock, category);
            }

            request.getSession().setAttribute("adminMessage", message);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("adminMessage", "Error: Price or Stock must be valid numbers.");
        } catch (Exception e) {
            request.getSession().setAttribute("adminMessage", "Operation failed: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/index?action=admin");
    }

    private void handlePackOrder(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String orderIdString = request.getParameter("orderId");

        if (orderIdString != null && !orderIdString.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdString);
                OrderHandler.pack(orderId);

            } catch (NumberFormatException e) {
                System.err.println("Invalid Order ID format: " + orderIdString);
            }
        }

        response.sendRedirect(request.getContextPath() + "/index?action=staff");
    }
}