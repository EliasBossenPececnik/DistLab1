package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.OrderDB;

import java.util.List;

public class OrderInfo {
    private int orderInfoID;
    private int orderID;
    private int itemID;
    private int quantity;

    public OrderInfo(int orderInfoID, int orderID, int itemID, int quantity) {
        this.orderInfoID = orderInfoID;
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public static List<OrderInfo> getOrder(int orderID){
        return OrderDB.getOrder(orderID);
    }

    public int getItemID() {
        return itemID;
    }

    public int getOrderID() {
        return orderID;
    }
}
