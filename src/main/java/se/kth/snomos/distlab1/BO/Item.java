package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.ItemDB;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Item(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Item() {}

    public static List<Item> getAllItems() {
        return ItemDB.getAllItems();
    }

    public static List<Item> getItemsByCategory(String category) {
        return ItemDB.getItemsByCategory(category);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Id: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Category: " + category;
        return info;
    }

}
