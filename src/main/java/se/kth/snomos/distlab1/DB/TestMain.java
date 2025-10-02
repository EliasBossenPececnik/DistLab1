package se.kth.snomos.distlab1.DB;

public class TestMain {
    public static void main(String[] args) {
        DBManager.getConnection();
        System.out.println(DBManager.getname("Spoon"));
    }
}
