package se.kth.snomos.distlab1.BO;

public class TestMain {
    public static void main(String[] args) {
        ShoppingCartHandler sh = new ShoppingCartHandler();
        sh.addItem(ItemHandler.getItemByName("Fork"));
        sh.addItem(ItemHandler.getItemByName("Fork"));
        System.out.println(sh.getCart());
        //OrderHandler.placeOrder(sh.getCart(),"Bossen");


    }
}
