package org.ferris.riviera.console.connection;

import java.sql.Connection;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ConnectionHandler {

    private Connection connection;

    protected ConnectionHandler(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
