package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Role;
import se.kth.snomos.distlab1.BO.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static se.kth.snomos.distlab1.BO.Role.*;

public class UserDB extends User {

    private UserDB(int id, String userName, String password, Role role) {
        super(id, userName, password, role);
    }

    public static int logIn(String username, String password) {
        if (username == null || password == null) {
            return 0;
        }
        if (UserDB.userExists(username) != -1){
            String query = "SELECT * FROM users WHERE usernameU = ? AND passwordU= ?";
            try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
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
        if (username == null || password == null || UserDB.userExists(username) != -1) {
            return false;
        }
        String query = "INSERT INTO users (usernameU,passwordU,roleU) Values (?,?,?)";
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, CUSTOMER.toString());
            statement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean promoteUser(String username, String role) {
        if (username == null || role == null || UserDB.userExists(username) == -1) {
            return false;
        }
        String queryGetUser = "Select userId From users Where usernameU = ?";
        String querySetRole = "Update users set roleU = ? where userid = ?";
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(queryGetUser)){
            statement.setString(1, username);
            ResultSet re = statement.executeQuery();
            if (re.next()) {
                try (PreparedStatement ps = DBManager.getConnection().prepareStatement(querySetRole)){
                    ps.setString(1, role);
                    ps.setInt(2, re.getInt("userId"));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static int userExists(String userName) {
        String query = "Select * from users Where usernameU = ?";
        try(PreparedStatement statement = DBManager.getConnection().prepareStatement(query)){
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("userId");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return -1;
    }
}
