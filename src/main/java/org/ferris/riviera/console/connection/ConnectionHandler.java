package org.ferris.riviera.console.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.inject.Inject;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionHandler {

    private Connection conn;

    @Inject
    @Key(value="url", required=true)
    protected String url;

    @Inject
    @Key(value="username", required=true)
    protected String username;

    @Inject
    @Key(value="password", required=true)
    protected String password;


    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return conn;
    }
}
