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
                request.setAttribute("shoppingCart", shoppingCartHandler.getCart());
                nextPage = "shoppingcart.jsp";
                break;
            case "staff" :
                nextPage = "staff.jsp";
                break;
            case "admin" :
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
                //ItemInfo itemToAdd = ItemHandler.getItemByName(item);

                //if (itemToAdd != null) {
                    //shoppingCartHandler.addItem(itemToAdd);
               // }

            } catch (NumberFormatException e) {
                System.err.println("Invalid item ID format: " + item);
            }
        }
        response.sendRedirect(request.getContextPath() + "/index?action=all");
    }
}