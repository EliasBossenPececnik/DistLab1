package se.kth.snomos.distlab1;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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
                List<ItemInformation> items = itemHandler.getAllItems();
                request.setAttribute("items", items);
                nextPage = "index.jsp";
                break;
            case "shoppingCart" :
                List<ItemInformation> shoppingCart = ShoppingCart.getAll();
                request.setAttribute("shoppingCart", shoppingCart);
                nextPage = "shoppingCart.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {

    }
}