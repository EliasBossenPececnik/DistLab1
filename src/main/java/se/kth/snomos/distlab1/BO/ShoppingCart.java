package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (item.getStock() > 0){
            items.add(item);
            return true;
        }
        return false;
    }

    public List<Item> getItems() {
        return items;
    }
}
