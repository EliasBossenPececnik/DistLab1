package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartHandler {

    private ShoppingCart cart;

    public ShoppingCartHandler() {
        cart = new ShoppingCart();
    }

    public boolean addItem(Item item) {
        return cart.addItem(item);
    }

    public List<ItemInfo> getCart(){
        List<ItemInfo> items = new ArrayList<>();
        for(Item item : cart.getItems()){
            items.add(new ItemInfo(item.getName(), item.getPrice(), item.getStock(), item.getCategory()));
        }
        return items;
    }
}
