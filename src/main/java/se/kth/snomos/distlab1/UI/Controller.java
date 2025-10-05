package se.kth.snomos.distlab1.UI;

import java.io.*;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import se.kth.snomos.distlab1.BO.ItemHandler;
import se.kth.snomos.distlab1.BO.ShoppingCart;

@WebServlet(name = "Controller", value = "/index")
public class Controller extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
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
                //List<ItemInformation> items = ItemHandler.getAllItems();
                //request.setAttribute("items", items);
                nextPage = "index.jsp";
                break;
            case "shoppingCart" :
                //List<ItemInformation> shoppingCart = ShoppingCartHandler.getAllItems();
                //request.setAttribute("shoppingCart", shoppingCart);
                nextPage = "shoppingCart.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "login" :
                handleLogin(request, response);
        }
    }

    public void destroy() {

    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginSuccess = UserHandler.validateLogin(username, password);
        if (loginSuccess) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/index?action=all");
        }
    }
}