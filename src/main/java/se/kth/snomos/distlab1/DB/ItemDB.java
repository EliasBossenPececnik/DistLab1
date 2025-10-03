package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Item;

import java.sql.ResultSet;
import java.sql.Statement;

public class ItemDB extends Item {

    private ItemDB(int id, String name, double price, int stock){
        super(id, name, price, stock);
    }

    public static void getItem(){
        String name = "";
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from item");
            while(resultSet.next()){
                name = resultSet.getString("name");
                name += ": " + resultSet.getDouble("price");
                System.out.println(name);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
