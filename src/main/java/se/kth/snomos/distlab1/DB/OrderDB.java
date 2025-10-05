package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Order;
import se.kth.snomos.distlab1.BO.OrderInfo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDB {

    public static List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultset = statement.executeQuery("SELECT * FROM orders");
            while (resultset.next()) {
                orders.add(new Order(resultset.getInt("orderId"),resultset.getInt("userId")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public static List<OrderInfo> getOrder(int orderID){
        List<OrderInfo> order = new ArrayList<>();
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultset = statement.executeQuery("SELECT * FROM orders WHERE orderID = " + orderID);
            while (resultset.next()) {
                order.add(new OrderInfo(resultset.getInt("orderinfoId"), resultset.getInt("orderId"),
                        resultset.getInt("itemId"), resultset.getInt("quantity")));
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return order;
    }
}
