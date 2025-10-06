package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartHandler {

    private ShoppingCart cart;

    public ShoppingCartHandler() {
        cart = new ShoppingCart();
    }

    public boolean addItem(ItemInfo item) {
        return cart.addItem(item);
    }

    public List<ItemInfo> getCart(){
        return cart.getItems();
    }

    public void clearCart(){
        cart.clearCart();
    }
}
