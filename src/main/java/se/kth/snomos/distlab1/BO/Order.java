package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.OrderDB;

import java.util.List;

public class Order {
    private int orderID;
    private int userID;

    public Order(int orderID, int userID) {
        this.orderID = orderID;
        this.userID = userID;
    }

    public static List<Order> getAllOrders(){
        return OrderDB.getAllOrders();
    }
}
