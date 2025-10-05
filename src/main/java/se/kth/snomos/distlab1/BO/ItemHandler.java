package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    public static List<ItemInfo> getAllItems(){
        List<ItemInfo> list = new ArrayList<>();
        for(Item i : Item.getAllItems()){
            list.add(new ItemInfo(i.getName(), i.getPrice(), i.getStock(),i.getCategory()));
        }
        return list;
    }
    public static List<ItemInfo> getItemsByCategory(String category){
        List<ItemInfo> list = new ArrayList<>();
        for(Item i : Item.getItemsByCategory(category)){
            list.add(new ItemInfo(i.getName(), i.getPrice(), i.getStock(),i.getCategory()));
        }
        return list;
    }
}
