package com.mycompany.observer;

/**
 *
 * @author Mees Buschman
 * Enum holding all states available.
 */
public enum ViewState {
    
    Login("Login.fxml"),
    Register("Register.fxml"),
    Main("Main.fxml"),
    createAccount("CreateAccount.fxml"), 
    AccountDetails("AccountDetail.fxml");

    private String name;
    
    private ViewState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
