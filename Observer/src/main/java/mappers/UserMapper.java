package mappers;

import com.mycompany.observer.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modules.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Gets data from database and creates User objects.
 * @author Mees Buschman
 */
public class UserMapper{
    /**
     * Imorts data in database.
     * @param username username of user.
     * @param password password of user (as hash).
     * @param firstname firstname of user.
     * @param lastname lastname of user.
     * @throws SQLException 
     */
    public static void importUser(String username, String password, String firstname, String lastname) throws SQLException{
        try{
                String sql = "INSERT INTO `user` (`username`, `password`, `firstName`, `lastName`) VALUES (?, ?, ?, ?);";

                PreparedStatement statement = Database.getConnection().prepareStatement(sql);

                statement.setString(1, username);
                statement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                statement.setString(3, firstname);
                statement.setString(4, lastname);

                statement.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }
    
    /**
     * Imports data in database with addon in name.
     * @param username username of user.
     * @param password password of user (as hash).
     * @param firstname firstname of user.
     * @param lastname lastname of user.
     * @param addon addon between first and lastname.
     * @throws SQLException 
     */
    public static void importUser(String username, String password, String firstname, String lastname, String addon) throws SQLException{
        
                String sql = "INSERT INTO `user` (`username`, `password`, `firstName`, `lastName`, `addonName`) VALUES (?, ?, ?, ?, ?);";

                PreparedStatement statement = Database.getConnection().prepareStatement(sql);

                statement.setString(1, username);
                statement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                statement.setString(3, firstname);
                statement.setString(4, lastname);
                statement.setString(5, addon);

                statement.executeUpdate();
        
    
    }
    
    /**
     * 
     * @param username username of the user filled in.
     * @return The user as an object.
     * @throws SQLException 
     */
    public static User getUser(String username) throws SQLException{
        String sql = "SELECT * FROM user WHERE username = binary ?";
        
        PreparedStatement stm = Database.getConnection().prepareStatement(sql);
        
        stm.setString(1, username);
        
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            String addon;
            try {
                addon = rs.getString("addonName");
            } catch (Exception e) {
                System.out.println("Has no addon!");
                addon = "";
            }
            User user = new User(rs.getInt("userId"), 
                    rs.getString("username"),
                    rs.getString("password"), 
                    rs.getString("firstName"), 
                    rs.getString("lastName"), 
                    addon);
            
            return user;
        }
        
        return null;
    }
    
    /**
     * 
     * @param username username of user.
     * @return the hashed password.
     * @throws SQLException 
     */
    public static String getUserPassword(String username) throws SQLException{
        String sql = "SELECT user.password FROM user WHERE username = ?";
        
        PreparedStatement stm = Database.getConnection().prepareStatement(sql);
        
        stm.setString(1, username);
        
        ResultSet rs = stm.executeQuery();
        
        if(rs.next()){
            return rs.getString("password");
        }
        return "";
    }
    
    /**
     * Sets the lastLogin in database to when user logs in.
     * @param username username of user.
     * @throws SQLException 
     */
    public static void loggedIn(String username) throws SQLException{
        String sql = "UPDATE user SET lastLogin = CURRENT_TIMESTAMP WHERE username = ?";
        
        PreparedStatement stm = Database.getConnection().prepareStatement(sql);
        
        stm.setString(1, username);
        
        stm.executeUpdate();
    }
}
