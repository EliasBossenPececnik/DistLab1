package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ItemInfo> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public boolean addItem(ItemInfo itemInfo) {
        if (itemInfo.getStock() > 0){
            items.add(itemInfo);
            return true;
        }
        return false;
    }

    public List<ItemInfo> getItems() {
        return items;
    }

    public void clearCart(){
        items.clear();
    }
}
