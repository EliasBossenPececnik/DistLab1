package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Item;

public class ItemDB extends Item {

    private ItemDB(int id, String name, double price, int stock){
        super(id, name, price, stock);
    }
}
