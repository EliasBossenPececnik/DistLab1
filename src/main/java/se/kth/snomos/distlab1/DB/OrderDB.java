package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDB {

    public static List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        Connection con = DBManager.getConnection();
        try(PreparedStatement statement = con.prepareStatement(query);){
            ResultSet resultset = statement.executeQuery();
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
        Connection con = DBManager.getConnection();
        try(Statement statement = con.createStatement()){
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

    public static void placeOrder(ShoppingCart cart, String username){
        int currentUserid = UserDB.userExists(username);
        int currentOrder;
        String orderQuery = "Insert into orders (userId) values (?)";
        String selectQuery = "Select * from orders where userId = ?";
        String orderInfo = "Insert into orderInfo (orderId, itemId, quantity) values (?, ?, ?)";
        String updateItem = "Update items set itemStock = itemStock - ? where itemId = ?";
        try{
            Connection connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(orderQuery);
                statement.setInt(1, currentUserid);
                statement.executeQuery();

                statement = connection.prepareStatement(selectQuery);
                statement.setInt(1, currentUserid);
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    currentOrder = rs.getInt("orderId");
                    for(CartItem i : cart.getCartItems()){
                        statement = connection.prepareStatement(updateItem);
                        statement.setInt(1, i.getQuantity());
                        statement.executeQuery();
                        statement = connection.prepareStatement(orderInfo);
                        statement.setInt(1, currentOrder);
                        statement.setInt(2,i.getItemID());
                        statement.setInt(3,i.getQuantity());
                        statement.executeQuery();
                    }
                }
                connection.commit();
            }catch (Exception e){
                connection.rollback();
            }finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
