package controllers;
import com.mycompany.observer.Parameter;
import com.mycompany.observer.Parameters;
import com.mycompany.observer.ViewState;
import com.mycompany.observer.ViewStore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import mappers.AccountMapper;
import modules.User;
import modules.Account;

/**
 * FXML Controller class
 *
 * @author Mees Buschman
 */
public class MainController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<Account, String> websiteColumn;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account, String> urlColumn;
    @FXML
    private TextField searchQuery;
    
    private User user;
    
    //List holding your accounts.
    private ObservableList<Account> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        ----Table stuff-----
        */
        websiteColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        list = FXCollections.observableArrayList();
        try {
            for(Account account : AccountMapper.getAccounts()){
                list.add(account);
            }
            
            table.setItems(list);
        } catch (SQLException ex) {
            System.out.println("No accounts in user " + user.getFistname());
        }
        
        // Add Event Listener on select
        table.setRowFactory(tv -> {
            TableRow<Account> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Account rowData = row.getItem();
                    this.handleRowDoubleClickAction(rowData);
                }
            });
            return row;
        });
        
        /*
        -------User welcome message------
        */
        user = (User) Parameters.getParameter("user");
        
        if (user.getAddon() != null) {
            welcome.setText(String.format("Welcome %s %s %s", user.getFistname(), user.getAddon(), user.getLastname()));
        } else {
            welcome.setText(String.format("Welcome %s %s", user.getFistname(), user.getLastname()));
        }
        
  
        searchQuery.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                String text = searchQuery.getText();
                ObservableList<Account> filteredList = FXCollections.observableArrayList();
                for(Account account : list){
                    if(account.getAccountName().contains(text)){
                        filteredList.add(account);
                    }
                }
                if(text.isEmpty()){
                    table.setItems(list);
                }else{
                    table.setItems(filteredList);
                }
            }
        });
    }
    
    /**
     * Handles add Account button.
     * @param event 
     */
    @FXML
    private void addAccount(ActionEvent event){
        ViewStore.getInstance().setView(ViewState.createAccount);
    }
    /**
     * Handles logout button.
     * @param event 
     */
    @FXML
    private void handleLogoutButton(ActionEvent event){
        ViewStore.getInstance().resetHistory();
        ViewStore.getInstance().setView(ViewState.Login);
    }

    /**
     * Handles row double click.
     * @param rowData 
     */
    private void handleRowDoubleClickAction(Account rowData) {
        Parameter row = new Parameter("account", rowData);
        
        ViewStore.getInstance().setView(ViewState.AccountDetails, new Parameters(row, new Parameter("user", user)));
        
    }

}
