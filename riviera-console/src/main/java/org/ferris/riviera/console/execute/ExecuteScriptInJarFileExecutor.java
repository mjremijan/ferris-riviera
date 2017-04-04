package org.ferris.riviera.console.execute;

import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteScriptInJarFileExecutor {

    @Inject
    protected Logger log;

    protected Statement stmt;

    @PostConstruct
    protected void postConstruct(ConnectionHandler handler) {
        try {
            stmt = handler.getConnection().createStatement();
        } catch (Exception e) {
            throw new RuntimeException(
                  String.format("Exception creating Statement object")
                , e
            );
        }
    }

    @ExecutionSkip
    protected void execute(String sql) {
        log.info("ENTER");
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Exception executing statement", e);
        }
    }
}
