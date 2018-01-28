package com.mycompany.observer;

/**
 *
 * @author Mees buschman
 * 
 */
public class Parameters {
    //Array holding Parameter objects.
    private static Parameter[] parameters;
    
    /**
     * 
     * @param parameters All paramaters to be hold.
     */
    public Parameters(Parameter... parameters) {
        this.parameters = parameters;
    }
    
    /**
     * 
     * @param key String key from Parameter.
     * @return Paramater Object holding the key.
     */
    public static Object getParameter(String key){
        System.out.println("----------------------------------------");
        for(Parameter parameter : parameters){
            System.out.println("Currently holding: " + parameter.getKey());
            if(parameter.getKey().equals(key)){
                return parameter.getObject();
            }
        }
        return null;
    }
    
    
}
