package org.ferris.riviera.console.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionHandlerProducer {

    private Connection connectionProxy;

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionProperties connectionProperties;

    @Produces
    protected ConnectionHandler produceConnectionHandler() {
        log.info(
            String.format(
                "Attempting connection to database: %s %s/%s"
                , connectionProperties.getUrl()
                , connectionProperties.getUsername()
                , connectionProperties.getPassword()
            )
        );
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                  connectionProperties.getUrl()
                , connectionProperties.getUsername()
                , connectionProperties.getPassword()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        log.info(
            String.format(
                "Attempting to proxy connection: %s"
                , String.valueOf(connection)
            )
        );
        Connection connectionProxy = (Connection)Proxy.newProxyInstance(
			    Connection.class.getClassLoader(),
			    new Class[]{Connection.class},
			    new ConnectionProxy(connection)
		);

        return new ConnectionHandler(connectionProxy);
    }

    public Connection getConnection() {
        return connectionProxy;
    }
}
