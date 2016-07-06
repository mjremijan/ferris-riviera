package org.ferris.riviera.console.connection;

import java.sql.Connection;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionHandler {

    private Connection conn;

    ConnectionHandler(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection() {
        return conn;
    }
}
