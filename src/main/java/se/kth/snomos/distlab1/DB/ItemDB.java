package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Item;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDB extends Item {

    private ItemDB(int id, String name, double price, int stock,String category) {
        super(id, name, price, stock, category);
    }

    public static List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from items");
            while(resultSet.next()){
                items.add(new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"),resultSet.getInt("itemStock"),
                        resultSet.getString("itemCategory")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static List<Item> getItemsByCategory(String category){
        List<Item> items = new ArrayList<>();
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from items where itemCategory = '"+category+"'");
            while(resultSet.next()){
                items.add(new Item(resultSet.getInt("itemId"), resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"),resultSet.getInt("itemStock"),
                        resultSet.getString("itemCategory")));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return items;
    }
}
