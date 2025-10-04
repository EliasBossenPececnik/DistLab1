package se.kth.snomos.distlab1.DB;

import se.kth.snomos.distlab1.BO.Role;
import se.kth.snomos.distlab1.BO.User;

import java.sql.ResultSet;
import java.sql.Statement;

public class userDB extends User {

    private userDB(int id, String userName, String password, Role role) {
        super(id, userName, password, role);
    }

    private boolean userExists(String userName) {
        try(Statement statement = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from users where username = '"+userName+"'");
            if(resultSet.next()){
                return true;
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        return false;
    }


}
