package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    public static List<ItemInfo> getAllItems(){
        List<ItemInfo> list = new ArrayList<>();
        for(Item i : Item.getAllItems()){
            list.add(new ItemInfo(i.getId(), i.getName(), i.getPrice(), i.getStock(),i.getCategory()));
        }
        return list;
    }
    public static List<ItemInfo> getItemsByCategory(String category){
        List<ItemInfo> list = new ArrayList<>();
        for(Item i : Item.getItemsByCategory(category)){
            list.add(new ItemInfo(i.getId(), i.getName(), i.getPrice(), i.getStock(),i.getCategory()));
        }
        return list;
    }
    public static ItemInfo getItemByName(String name){
        Item i = Item.getItemByName(name);
        return new ItemInfo(i.getId(), i.getName(), i.getPrice(), i.getStock(),i.getCategory());
    }
    public static void addItem(String name, double price, int stock,String category){
        Item.addItem(name, price, stock, category);
    };
    public static void updateItem(String name, double price, int stock, String category){
        Item.editItem(name,price,stock,category);
    }
}
