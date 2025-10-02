package se.kth.snomos.distlab1.BO;

public class Item {
    private int id;
    private String name;
    private double price;
    private int stock;

    protected Item(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Item() {}


}
