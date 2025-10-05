package se.kth.snomos.distlab1.BO;

import se.kth.snomos.distlab1.DB.UserDB;

public class User {
    private int id;
    private String userName;
    private String password;
    private Role role;

    public User(int id, String userName, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public static int logIn(String username, String password) {
        return UserDB.logIn(username, password);
    }

    public static boolean addUser(String username, String password) {
        return UserDB.addUser(username, password);
    }

    public static boolean promoteUser(String username, String password) {
        return UserDB.promoteUser(username, password);
    }

    @Override
    public String toString() {
        String info = "";
        info += "ID: " + this.id + " UserName: " + this.userName + " Password: " + this.password + " Role: " + this.role;
        return info;
    }
}
