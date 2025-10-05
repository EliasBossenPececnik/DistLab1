package se.kth.snomos.distlab1.BO;

public class TestMain {
    public static void main(String[] args) {
        ShoppingCartHandler handler = new ShoppingCartHandler();
        System.out.println(handler.getCart());
        System.out.println(ItemHandler.getAllItems());
        System.out.println(ItemHandler.getItemsByCategory("ELECTRONICS"));
        System.out.println(UserHandler.logIn("Johkar","Jocke2"));
    }
}
