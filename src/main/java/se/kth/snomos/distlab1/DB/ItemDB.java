package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Category;
import se.kth.snomos.distlab1.BO.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDB extends Item {

    private ItemDB(int id, String name, double price, int stock,Category category) {
        super(id, name, price, stock, category);
    }

    public static List<Item> getAllItemsDB(){
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        Connection con = DBManager.getConnection();
        try(PreparedStatement statement = con.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                items.add(new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"),resultSet.getInt("itemStock"),
                        Category.valueOf(resultSet.getString("itemCategory"))));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static List<Item> getItemsByCategoryDB(String category){
        List<Item> items = new ArrayList<>();
        String query = "select * from items where itemCategory = ?";
        Connection con = DBManager.getConnection();
        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                items.add(new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"),resultSet.getInt("itemStock"),
                        Category.valueOf(resultSet.getString("itemCategory"))));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return items;
    }

    public static Item getItemByName(String name){
        String query = "select * from items where itemName = ?";
        Connection connection = DBManager.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"), resultSet.getInt("itemStock"),
                        Category.valueOf(resultSet.getString("itemCategory")));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void addItem(String name, double price, int stock, String category){
        String query = "Insert into items (itemName, itemPrice, itemStock) values (?, ?, ?, ?)";
        Connection con = DBManager.getConnection();
        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, stock);
            statement.setString(4, category);
            statement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void editItem(String name, double price, int stock, String category) {
        String getQuery = "SELECT itemId FROM items WHERE itemName = ?";
        String updateQuery = "UPDATE items SET itemPrice = ?, itemStock = ?, itemCategory = ? WHERE itemId = ?";
        Connection conn = DBManager.getConnection();
        try (PreparedStatement getStmt = conn.prepareStatement(getQuery)) {

            getStmt.setString(1, name);
            try (ResultSet rs = getStmt.executeQuery()) {
                if (rs.next()) {
                    int itemId = rs.getInt("itemId");
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setDouble(1, price);
                        updateStmt.setInt(2, stock);
                        updateStmt.setString(3, category);
                        updateStmt.setInt(4, itemId);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
