package se.kth.snomos.distlab1.BO;

public enum Role {
    ADMIN("Admin"),STAFF("Staff"),CUSTOMER("Customer");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}
