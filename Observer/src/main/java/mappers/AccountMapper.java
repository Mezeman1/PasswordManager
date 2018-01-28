package mappers;

import com.mycompany.observer.Database;
import com.mycompany.observer.Parameters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modules.Account;
import modules.User;

/**
 * Gets data from database and creates Account objects.
 * @author Mees Buschman
 */
public class AccountMapper {
    
    /**
     * 
     * @return ArrayList holding all accounts from user.
     * @throws SQLException 
     */
    public static ArrayList<Account> getAccounts() throws SQLException {
        
        ArrayList<Account> list = new ArrayList<>();
        
        String sql = "SELECT * FROM account WHERE account.user_userId = ?";
        
        PreparedStatement statement = Database.getConnection().prepareStatement(sql);
        
        User user = (User) Parameters.getParameter("user");
        
        statement.setInt(1, user.getId());
        
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            Account account = new Account(rs.getInt("accountId"), 
                    rs.getString("accountName"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("url"));
            
            list.add(account);
        }
        
        return list;
       
    }

    /**
     * Inserts account data in database.
     * @param accountName Name of account.
     * @param username Username of the account.
     * @param password Password of the account.
     * @param url Url of the account.
     * @throws SQLException 
     */
    public static void insertAccount(String accountName, String username, String password, String url) throws SQLException {
        String sql = "INSERT INTO `account` (`accountName`, `username`, `password`, `url`, `user_userId`) VALUES (?, ?, ?, ?, ?);";
        
        PreparedStatement statement = Database.getConnection().prepareStatement(sql);
        
        User user = (User) Parameters.getParameter("user");
        System.out.println(user.getId());
                statement.setString(1, accountName);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, url);
                statement.setInt(5, user.getId());
                

                statement.executeUpdate();
    }

    /**
     * removes Account data from database.
     * @param account account that has been double clicked.
     * @throws SQLException 
     */
    public static void removeAccount(Account account) throws SQLException {
        String sql = "DELETE FROM `test`.`account` WHERE `accountId`=?;";
        
        PreparedStatement statement = Database.getConnection().prepareStatement(sql);
        
        statement.setInt(1, account.getId());
        
        statement.executeUpdate();
    }

    /**
     * Updates the values in Database.
     * @param account the account that has been double clicked.
     * @throws SQLException 
     */
    public static void updateAccount(Account account) throws SQLException {
        String sql = "UPDATE `account` SET accountName = ?, username = ?,"
                + " password = ?, url = ? WHERE accountId = ?"; 
        
        PreparedStatement statement = Database.getConnection().prepareStatement(sql);
        
        statement.setString(1, account.getAccountName());
        statement.setString(2, account.getUsername());
        statement.setString(3, account.getHash());
        statement.setString(4, account.getUrl());
        statement.setInt(5, account.getId());
        
        statement.executeUpdate();
        
        System.out.println("Updated correctly");
        
    }
    
}
