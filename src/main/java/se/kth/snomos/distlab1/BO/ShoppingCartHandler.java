package se.kth.snomos.distlab1.BO;


public class ShoppingCartHandler {

    private ShoppingCart cart;

    public ShoppingCartHandler() {
        cart = new ShoppingCart();
    }

    public boolean addItem(ItemInfo item) {
        return cart.addItem(item);
    }

    public ShoppingCart getCart(){
        return this.cart;
    }

    public void clearCart(){
        cart.clearCart();
    }
}
