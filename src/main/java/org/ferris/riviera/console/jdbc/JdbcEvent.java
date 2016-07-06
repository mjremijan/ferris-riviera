package org.ferris.riviera.console.jdbc;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcEvent {
    public static final int ADD_DRIVER_JAR_FILE_TO_SYSTEM_CLASSLOADER = 20;

    public static final int LOAD_CONNECTION_PROPERTIES = 30;

    public static final int VIEW = 40;

    protected String url;
    protected String username;
    protected String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
