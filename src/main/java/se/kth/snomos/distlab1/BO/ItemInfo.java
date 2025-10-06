package se.kth.snomos.distlab1.BO;

public class ItemInfo {
    private int itemId;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public ItemInfo(int itemId, String name, double price, int stock, Category category) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Category: " + category;
        return info;
    }
}
