package com.mycompany.observer;

import java.util.Observable;
import java.util.Stack;

/**
 * 
 * @author Mees Buschman
 * ViewStore as Observable.
 */
public class ViewStore extends Observable{
    
    /**
     * Holds the history of the states.
     */
    private Stack<ViewState> history = new Stack<>();
    
    /**
     * Gets the history of the states.
     */
    public Stack<ViewState> getHistory() {
        return history;
    }
    /**
     * Sets the history of the states.
     */
    public void addHistory(ViewState state){
        history.push(state);
        history.push(ViewState.Login);
    }
    /**
     * Resets the history to nothing.
     */
    public void resetHistory(){
        history.clear();
    }

    /**
     * Holds the current instance.
     */
    private static ViewStore instance = null;
    
    /**
     * Holds the parameters.
     */
    private Parameters parameters;
    
    /**
     * Holds the currentView.
     */
    private ViewState currentView;

    /**
     * Sets the first view to Login view.
     */
    public ViewStore() {
        this.currentView = ViewState.Login;
    }
    
    /**
     * 
     * @return the current view.
     */
    public static ViewStore getInstance(){
        if(instance == null){
            instance = new ViewStore();
        }
        return instance;
    }
    
    /**
     * 
     * @param newView the new view you want to set it to without parameters.
     */
    public void setView(ViewState newView){
        history.push(newView);
        this.setView(newView, null);
    }
    
    /**
     * 
     * @param newView the new view you want to set it to.
     * @param parameters the parameters you want to send with the view.
     */
    public void setView(ViewState newView, Parameters parameters){
        this.currentView = newView;
        this.parameters = parameters;
        if(parameters != null){
            history.push(newView);
        }
        
        setChanged();
        notifyObservers(this.currentView);
    }
    
    /**
     * Method to go back to the previous view.
     */
    public void goBack() {
        history.pop();
        this.currentView = history.peek();
        setChanged();
        notifyObservers(this.currentView);
    }
    
    /**
     * Method to go back to the previous view.
     * @param parameters parameters it needs to implement.
     */
    public void goBack(Parameters parameters) {
        history.pop();
        this.currentView = history.peek();
        this.parameters = parameters;
        setChanged();
        notifyObservers(this.currentView);
    }

    /**
     * Getter for the Current View property.
     *
     * @return last history - 2;
     */
    public ViewState getLast() {
        return history.get(history.size() - 2);
    }
    
    /**
     * Getter for the Current View property.
     *
     * @return current view.
     */
    public ViewState getCurrentView() {
        return this.currentView;
    }
    
    /**
     * 
     * @return current parameters.
     */
    public Parameters getCurrentParameters() {
        return parameters;
    }
}
