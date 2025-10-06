package se.kth.snomos.distlab1.BO;

public class CartItem {
    private int itemID;
    private int quantity;

    public CartItem(int itemID, int quantity){
        this.itemID = itemID;
        this.quantity = quantity;
    }
    public int getItemID() {
        return itemID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean containsItem(int itemID){
        return itemID == this.itemID;
    }

    @Override
    public String toString() {
        return "CartItem{" + "itemID=" + itemID + ", quantity=" + quantity + '}';
    }
}
