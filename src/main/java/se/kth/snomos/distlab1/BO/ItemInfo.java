package se.kth.snomos.distlab1.BO;

public class ItemInfo {
    private String name;
    private double price;
    private int stock;
    private Category category;

    public ItemInfo(String name, double price, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Category: " + category;
        return info;
    }
}
