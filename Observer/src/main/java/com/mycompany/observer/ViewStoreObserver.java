package com.mycompany.observer;

import java.util.Observer;
import com.mycompany.observer.ViewState;
import com.mycompany.observer.ViewStore;
import java.util.Observable;

/**
 *
 * @author Mees Buschman
 * ViewStoreObsver interface.
 */
public interface ViewStoreObserver extends Observer{
    /**
     * 
     * @param store 
     * @param state The state as an Enum to hold the FXML path.
     */
    void update(ViewStore store, ViewState state);
    
    /**
     * 
     * @param obs Sets the Viewstore as observable.
     * @param ob Sets the object to update.
     */
    @Override
    default void update(Observable obs, Object ob) {
        this.update((ViewStore) obs, (ViewState) ob);
    }
}
