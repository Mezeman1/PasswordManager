
package modules;

import java.util.List;

/**
 * User Class.
 * @author Mees Buschman
 */
public class User {
    private int id;
    private String username;
    private String hash;
    private String fistname;
    private String lastname;
    private String addon;
    private List<Account> accounts;

    public User(int id, String username, String hash, String fistname, String lastname, String addon) {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.fistname = fistname;
        this.lastname = lastname;
        this.addon = addon;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }
    
    
}
