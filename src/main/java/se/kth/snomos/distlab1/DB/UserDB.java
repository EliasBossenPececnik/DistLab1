package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Role;
import se.kth.snomos.distlab1.BO.User;

import java.sql.ResultSet;
import java.sql.Statement;

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
