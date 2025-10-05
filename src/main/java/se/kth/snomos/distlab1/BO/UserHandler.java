package se.kth.snomos.distlab1.BO;

public class UserHandler {
    public static int logIn(String username, String password) {
        return User.logIn(username,password);
    }

    public static boolean addUser(String username, String password){
        return User.addUser(username,password);
    }

    public static boolean promoteUser(String username, String password){
        return User.promoteUser(username,password);
    }
}
