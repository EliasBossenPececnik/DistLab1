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
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)){
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
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)){
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
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"), resultSet.getInt("itemStock"),
                        Category.valueOf(resultSet.getString("itemCategory")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void addItem(String name, double price, int stock, String category){
        String query = "Insert into items (itemName, itemPrice, itemStock) values (?, ?, ?, ?)";
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)){
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, stock);
            statement.setString(4, category);
            statement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void editItem(String name, double price, int stock, String category){
        String getQuery = "Select * from items where itemName = ?";
        String insertQuery = "Update items set itemPrice = ?, itemStock = ?, itemCategory = ? where itemId = ?";
        try(Connection conn = DBManager.getConnection()){
            PreparedStatement statement = conn.prepareStatement(getQuery);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                statement = conn.prepareStatement(insertQuery);
                statement.setDouble(1, price);
                statement.setInt(2, stock);
                statement.setString(3, category);
                statement.setInt(4, rs.getInt("itemId"));
                statement.executeUpdate();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
