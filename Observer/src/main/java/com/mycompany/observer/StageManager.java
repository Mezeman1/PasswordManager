package com.mycompany.observer;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
/**
 *
 * @author Mees Buschman
<<<<<<< HEAD
 * Stage as an Observer.
 * 
=======
 *
>>>>>>> 69159af4ee00d2c54bc1e04c05df2f3cfa1394a1
 */
public class StageManager implements ViewStoreObserver{
    
    private final Stage stage;
    private final int width;
    private final int height;

    public StageManager(Stage stage, int width, int height) {
        this.stage = stage;
        this.width = width;
        this.height = height;
        
        ViewStore.getInstance().addObserver(this);
    }

    /**
     * Updates the view on new notify.
     * @param store the ViewStore holding all the information.
     * @param state the ViewState holding the fxml.
     */
    @Override
    public void update(ViewStore store, ViewState state) {
        try {
            loadLayout(state.getName());
        } catch (IOException ex) {
            System.out.println("There was a problem with the state.");
        }
    }
  
    /**
     * Loads the layout as fxml.
     * @param layout String defined in ViewState.
     * @throws IOException 
     */
    public void loadLayout(String layout) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(String.format("/fxml/%s", layout)));
        
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }
    
    
}
