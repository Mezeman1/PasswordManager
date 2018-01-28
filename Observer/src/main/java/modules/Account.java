
package modules;

/**
 * Account Class.
 * @author Mees Buschman
 */
public class Account {
    private int id;
    private String accountName;
    private String username;
    private String hash;
    private String url;

    public Account(int id, String accountName, String username, String hash, String url) {
        this.id = id;
        this.accountName = accountName;
        this.username = username;
        this.hash = hash;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
