
package controllers;

import com.mycompany.observer.Parameters;
import com.mycompany.observer.ViewStore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mappers.AccountMapper;
import modules.Account;
import modules.User;

/**
 * FXML Controller class
 *
 * @author Mees Buschman
 */
public class AccountDetailController implements Initializable {

    @FXML
    private TextField accountnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField urlField;
    
    private User user;
    private Account account;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Gets the account from from paramaters.
        account = (Account) Parameters.getParameter("account");
        
        accountnameField.setText(account.getAccountName());
        usernameField.setText(account.getUsername());
        passwordField.setText(account.getHash());
        urlField.setText(account.getUrl());
    }    
    
    /**
     * Handles the back button.
     */
    @FXML
    private void handleBackButton(ActionEvent event){
        ViewStore.getInstance().goBack();
    }
    
    /**
     * Handles the remove button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleRemoveButton(ActionEvent event) throws SQLException{
        
        AccountMapper.removeAccount(account);
        ViewStore.getInstance().goBack();
    }
    
    /**
     * Handles the update button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleUpdateButton(ActionEvent event) throws SQLException{
        account.setAccountName(accountnameField.getText());
        account.setHash(passwordField.getText());
        account.setUrl(urlField.getText());
        account.setUsername(usernameField.getText());
        
        AccountMapper.updateAccount(account);
    }
}
