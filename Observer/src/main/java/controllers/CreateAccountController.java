
package controllers;

import com.mycompany.observer.ViewState;
import com.mycompany.observer.ViewStore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mappers.AccountMapper;
import modules.Account;

/**
 * FXML Controller class
 *
 * @author Mees Buschman
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField accountnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField urlField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * Handles the add account button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleAddAccount(ActionEvent event) throws SQLException{
        if(checkFilled()){
            AccountMapper.insertAccount(accountnameField.getText(), 
                    usernameField.getText(), 
                    passwordField.getText(), 
                    urlField.getText());
            
            ViewStore.getInstance().setView(ViewState.Main);
        }else{
            System.out.println("Change to label changing text!");
        }
    }

    /**
     * Checks if everything is filled.
     * @return 
     */
    private boolean checkFilled() {
        if(!accountnameField.getText().isEmpty()
                && !usernameField.getText().isEmpty()
                && !passwordField.getText().isEmpty()
                && !urlField.getText().isEmpty()){
            return true;
        }
        return false;
    }
    
    /**
     * Handles the back button.
     * @param event 
     */
    @FXML
    private void handleBackButton(ActionEvent event){
        ViewStore.getInstance().goBack();
    }
}
