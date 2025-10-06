package se.kth.snomos.distlab1.BO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;
    private List<ItemInfo> itemInfos;

    public ShoppingCart() {
        items = new ArrayList<>();
        itemInfos = new ArrayList<>();
    }

    public boolean addItem(ItemInfo itemInfo) {
        if (itemInfo.getStock() > 0){
            itemInfos.add(itemInfo);
            for(CartItem item : items){
                if(item.getItemID() == itemInfo.getItemID()){
                    item.setQuantity(item.getQuantity()+1);
                }
            }
            return true;
        }
        return false;
    }

    public List<ItemInfo> getItems() {
        return itemInfos;
    }

    public List<CartItem> getCartItems() {
        return items;
    }

    public void clearCart(){
        items.clear();
    }
    @Override
    public String toString() {
        return "ShoppingCart{" + "items=" + items + '}';
    }
}
