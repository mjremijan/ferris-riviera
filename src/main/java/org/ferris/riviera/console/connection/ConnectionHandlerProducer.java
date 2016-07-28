package org.ferris.riviera.console.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ConnectionHandlerProducer {

    private ConnectionHandler connectionHandler;

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionProperties connectionProperties;

    @Produces
    protected ConnectionHandler produceConnectionHandler() {
        if (connectionHandler == null) 
        {
            log.info(
                    String.format(
                            "Attempting connection to database: %s %s/%s", connectionProperties.getUrl(), connectionProperties.getUsername(), connectionProperties.getPassword()
                    )
            );
            
            
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(
                        connectionProperties.getUrl(), connectionProperties.getUsername(), connectionProperties.getPassword()
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            log.info(
                    String.format(
                            "Attempting to proxy connection: %s", String.valueOf(connection)
                    )
            );
            
            
            Connection connectionProxy = (Connection) Proxy.newProxyInstance(
                    Connection.class.getClassLoader(),
                    new Class[]{Connection.class},
                    new ConnectionProxy(connection)
            );

            if (connectionProperties.getSetSchema() != null) {
                log.info(
                        String.format(
                                "Attempting to change the database schema: '%s'", String.valueOf(connectionProperties.getSetSchema())
                        )
                );
                try (Statement stmt = connectionProxy.createStatement();) {
                    stmt.execute(connectionProperties.getSetSchema());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        
            connectionHandler
                = new ConnectionHandler(connectionProxy);
        }
        
        return connectionHandler;        
    }
}
