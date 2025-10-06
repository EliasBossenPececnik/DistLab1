package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.ItemDB;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Item(int id, String name, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static List<Item> getAllItems() {
        return ItemDB.getAllItemsDB();
    }

    public static List<Item> getItemsByCategory(String category) {
        return  ItemDB.getItemsByCategoryDB(category);
    }

    public static Item getItemByName(String name){
        return ItemDB.getItemByName(name);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Id: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Category: " + category;
        return info;
    }
    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getStock(){
        return stock;
    }
    public Category getCategory(){
        return category;
    }

}
