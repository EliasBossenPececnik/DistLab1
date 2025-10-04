package se.kth.snomos.distlab1.BO;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(ItemHandler.getAllItems());
        System.out.println(ItemHandler.getItemsByCategory("ELECTRONICS"));
    }
}
