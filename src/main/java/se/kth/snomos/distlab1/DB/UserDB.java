package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Role;
import se.kth.snomos.distlab1.BO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static se.kth.snomos.distlab1.BO.Role.*;

public class UserDB extends User {

    private UserDB(int id, String userName, String password, Role role) {
        super(id, userName, password, role);
    }

    public static int logIn(String username, String password) {
        if (username == null || password == null) {
            return 0;
        }
        if (UserDB.userExists(username)){
            try(Statement statement = DBManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery("select roleU from users where usernameU = '"+username+"' AND passwordU = '"+password+"'");
                if (resultSet.next()) {
                    return switch (resultSet.getString("roleU")) {
                        case "ADMIN" -> 1;
                        case "STAFF" -> 2;
                        case "CUSTOMER" -> 3;
                        default -> 0;
                    };
                }

            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return 0;
    }

    public static boolean addUser(String username, String password) {
        if (username == null || password == null || UserDB.userExists(username)) {
            return false;
        }
        try(Statement statement = DBManager.getConnection().createStatement()){
            statement.executeUpdate("INSERT INTO users(usernameU,passwordU,roleU) VALUES('"+username+"','"+password+"', '"+CUSTOMER+"')");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean promoteUser(String username, String role) {
        if (username == null || role == null || !UserDB.userExists(username)) {
            return false;
        }
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet re = statement.executeQuery("Select userId From users Where usernameU = '"+username+"'");
            if (re.next()) {
                statement.executeUpdate("Update users set roleU = '"+role+"' where userId = '"+re.getInt("userId")+"'");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static boolean userExists(String userName) {
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from users where usernameU = '"+userName+"'");
            if(resultSet.next()){
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }


}
