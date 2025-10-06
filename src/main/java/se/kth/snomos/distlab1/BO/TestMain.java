package se.kth.snomos.distlab1.BO;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(ItemHandler.getItemByName("Fork"));
        ItemHandler.updateItem("Fork",2,10,"Houseware");
        System.out.println(ItemHandler.getItemByName("Fork"));
        /*
        ShoppingCartHandler sh = new ShoppingCartHandler();
        sh.addItem(ItemHandler.getItemByName("Fork"));
        System.out.println(sh.getCart());*/
    }
}
