package se.kth.snomos.distlab1.BO;

public enum Category {
    ELECTRONICS("Electronics"),HYGIEN("Hygien"),BOOKS("Books"),HOUSEWARE("Houseware"),EDUCATIONAL("Educational"),
            CLOTHING("Clothing"),TOY("Toy"),GAME("Game"),CONSUMABLE("Consumable"),MISC("Misc");

    private String category;

    Category(String category){
        this.category = category;
    }
}
