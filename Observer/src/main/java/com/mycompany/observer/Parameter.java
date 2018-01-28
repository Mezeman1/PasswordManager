package com.mycompany.observer;

/**
 *
 * @author Mees Buschman
 */
public class Parameter {
    //Holds the key
    private String key;

    //Holds the object
    private Object object;

    public Parameter(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    public String getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }
    
    
    
}
