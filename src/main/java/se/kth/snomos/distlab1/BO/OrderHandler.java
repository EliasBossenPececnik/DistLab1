package se.kth.snomos.distlab1.BO;

import java.util.List;

public class OrderHandler {
    public static List<Order> getAllOrders(){
        return Order.getAllOrders();
    }
    public static List<OrderInfo> getOrder(int orderID){
        return OrderInfo.getOrder(orderID);
    }
}
