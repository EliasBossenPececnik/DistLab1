package se.kth.snomos.distlab1.BO;

public class UserHandler {
    public static int logIn(String username, String password) {
        return User.logIn(username,password);
    }
}
