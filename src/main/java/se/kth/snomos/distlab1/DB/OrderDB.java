package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.*;

import java.sql.*;
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

    public static void placeOrder(ShoppingCart cart, String username) {
        int userId = UserDB.userExists(username);
        if (userId < 0) {
            throw new IllegalArgumentException("Användaren hittades inte: " + username);
        }

        final String INSERT_ORDER = "INSERT INTO orders (userId) VALUES (?)";
        final String INSERT_ORDER_INFO = "INSERT INTO orderinfo (orderId, itemId, quantity) VALUES (?, ?, ?)";
        final String UPDATE_ITEM_STOCK = "UPDATE items SET itemStock = itemStock - ? WHERE itemId = ?";
        Connection connection = null;
        int newOrderId;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement orderStmt = connection.prepareStatement(
                    INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {

                orderStmt.setInt(1, userId);
                int affectedRows = orderStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Kunde inte skapa orderhuvudet.");
                }

                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newOrderId = generatedKeys.getInt(1);

                    } else {
                        throw new SQLException("Kunde inte hämta genererat orderId.");
                    }
                }
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(UPDATE_ITEM_STOCK);
                 PreparedStatement infoStmt = connection.prepareStatement(INSERT_ORDER_INFO)) {

                for (CartItem item : cart.getCartItems()) {
                    System.out.println("item.getItemID()" + "item.getQuantity()");
                    updateStmt.setInt(1, item.getQuantity());
                    updateStmt.setInt(2, item.getItemID());
                    updateStmt.addBatch();

                    infoStmt.setInt(1, newOrderId);
                    infoStmt.setInt(2, item.getItemID());
                    infoStmt.setInt(3, item.getQuantity());
                    infoStmt.addBatch();
                }

                updateStmt.executeBatch();
                infoStmt.executeBatch();
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    System.err.println("Transaktion misslyckades. Gör rollback.");
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    throw new RuntimeException("Rollback misslyckades.", rollbackEx);
                }
            }
            throw new RuntimeException("Fel vid placering av ordern.", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeEx) {
                    throw new RuntimeException("Fel vid stängning av anslutningen.", closeEx);
                }
            }
        }
    }

    public static void pack(int orderID){
        String query = "DELETE FROM orders WHERE orderId = ?";
        Connection con = DBManager.getConnection();
        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setInt(1, orderID);
            statement.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
