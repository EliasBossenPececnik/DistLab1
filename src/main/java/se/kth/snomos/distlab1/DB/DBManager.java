package se.kth.snomos.distlab1.DB;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private Connection connection = null;

    private static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/warehouse","root","Admin123");
            System.out.println("Connected to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return getInstance().connection;
    }

    public static String getname(String query){
        String name = "";
        try(Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select name from item WHERE name = '"+query+"'");
            while(resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}