package se.kth.snomos.distlab1.BO;

import java.util.List;

public class ItemHandler {
    public static List<Item> getAllItems(){
        return Item.getAllItems();
    }
    public static List<Item> getItemsByCategory(String category){
        return Item.getItemsByCategory(category);
    }
}
