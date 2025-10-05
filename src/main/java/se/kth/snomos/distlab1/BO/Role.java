package se.kth.snomos.distlab1.BO;

public enum Role {
    ADMIN("Admin"),STAFF("Staff"),CUSTOMER("Customer");

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
