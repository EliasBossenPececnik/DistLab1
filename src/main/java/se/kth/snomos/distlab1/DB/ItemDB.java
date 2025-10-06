package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Category;
import se.kth.snomos.distlab1.BO.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDB extends Item {

    private ItemDB(int id, String name, double price, int stock,Category category) {
        super(id, name, price, stock, category);
    }

    public static List<Item> getAllItemsDB(){
        List<Item> items = new ArrayList<>();
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from items");
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
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from items where itemCategory = '"+category+"'");
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
}
