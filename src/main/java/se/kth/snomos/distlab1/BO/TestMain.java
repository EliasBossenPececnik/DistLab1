package se.kth.snomos.distlab1.BO;

public class TestMain {
    public static void main(String[] args) {
        ShoppingCartHandler sh = new ShoppingCartHandler();
        sh.addItem(ItemHandler.getItemByName("Fork"));

        OrderHandler.placeOrder(sh.getCart(),"Bossen");

        System.out.println(sh.getCart());
    }
}
