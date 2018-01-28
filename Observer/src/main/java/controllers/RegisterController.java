
package controllers;

import com.mycompany.observer.Parameter;
import com.mycompany.observer.Parameters;
import mappers.UserMapper;
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

/**
 * FXML Controller class
 *
 * @author Mees Buschman
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField addOnField;
    @FXML
    private TextField lastNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    /**
     * Handles the register button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleRegisterButton(ActionEvent event) throws SQLException {
        if (fieldsFilled()) {
            if (passAreSame()) {
                if (addOnField.getText().isEmpty()) {
                    UserMapper.importUser(usernameField.getText(), 
                            passwordField1.getText(), 
                            firstNameField.getText(), 
                            lastNameField.getText());
                } else {
                    UserMapper.importUser(usernameField.getText(), 
                            passwordField1.getText(), 
                            firstNameField.getText(), 
                            lastNameField.getText(), 
                            addOnField.getText());
                }
                ViewStore.getInstance().setView(ViewState.Login, new Parameters(new Parameter("registerCorrect", "Your account has been created.")));
            }
        }
    }
    /**
     * Checks if all fields are filled in.
     * @return 
     */
    private boolean fieldsFilled() {
        if (!usernameField.getText().isEmpty()
                && !passwordField1.getText().isEmpty()
                && !passwordField2.getText().isEmpty()
                && !firstNameField.getText().isEmpty()
                && !lastNameField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    //Checks if password is same in both fields.
    private boolean passAreSame() {
        if (passwordField1.getText().equals(passwordField2.getText())) {
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
