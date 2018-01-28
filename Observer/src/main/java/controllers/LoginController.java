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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modules.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Initializable {
    
    @FXML
    private Label mainLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    
    private User user;
    
    /**
     * handles the register button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleRegisterButton(ActionEvent event) throws SQLException {
        ViewStore.getInstance().setView(ViewState.Register);
    }
    
    /**
     * Handles the login button.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException {
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        //Check if fields are filled in.
        if(checkFields()){ 
            //Checks if username filled in excists in database and returns user object.
            try {
                user = UserMapper.getUser(username);
                //Checks if the password filled in is correct.
                if(BCrypt.checkpw(password, UserMapper.getUserPassword(username))){
                    UserMapper.loggedIn(username);
                    ViewStore.getInstance().setView(ViewState.Main, new Parameters(new Parameter("user", user)));
                }else{
                    mainLabel.setText("Your username and/or password is wrong.");
                }
            } catch (Exception e) {
                mainLabel.setText("An user with that username does not excist.");
            }
                  
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            mainLabel.setText((String)Parameters.getParameter("registerCorrect"));
        }catch(Exception e){
            mainLabel.setText("Fill in your username and password to log in.");
        }
    }    
    /**
     * Checks if all fields are filled in.
     * @return 
     */
    private boolean checkFields() {
       if(!passwordField.getText().isEmpty() &&
               !usernameField.getText().isEmpty()){
           return true;
       }
       return false;
    }

}
