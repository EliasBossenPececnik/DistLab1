package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.OrderDB;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private int userID;
    private final List<OrderInfo> items;

    public Order(int orderID, int userID) {
        this.orderID = orderID;
        this.userID = userID;
        this.items = new ArrayList<>();
    }

    public static List<Order> getAllOrders(){
        return OrderDB.getAllOrders();
    }

    public static void placeOrder(ShoppingCart cart, String username){
        OrderDB.placeOrder(cart,username);
    }
}
