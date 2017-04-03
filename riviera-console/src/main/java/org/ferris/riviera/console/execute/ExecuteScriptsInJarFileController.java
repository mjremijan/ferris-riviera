package org.ferris.riviera.console.execute;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.execute.ExecuteEvent.EXECUTE_SCRIPTS_IN_JAR_FILE;
import org.ferris.riviera.console.jar.JarEntryStatements;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteScriptsInJarFileController {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ExecuteScriptsInJarFilePage page;

    @Inject
    protected ExecuteScriptHistoryInsert insert;

    @Inject
    protected ExecuteProperties executeProperties;

    public void observeExecuteScriptsInJarFile(
        @Observes @Priority(EXECUTE_SCRIPTS_IN_JAR_FILE) ExecuteEvent event
    ) {
        log.info("ENTER");

        Connection conn;
        Statement stmt;
        try {
            conn = handler.getConnection();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            event.setFailed(
                new RuntimeException("Unable to either get connection to database or create the Statement", e)
            );
            return;
        }

        try {
            event.getJarEntries().stream().forEach(je -> {
                page.showFileThatsBeingRead(je.getName());

                JarEntryStatements statements
                    = event.getJarFile().getJarEntryStatements(je);

                statements.stream().forEach(sql -> {
                    page.showSQLStatementThatsBeingExecuted(sql);
                    try {
                        if (executeProperties.getExecuteSql()) {
                            stmt.execute(sql);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException("Exception executing statement", e);
                    }
                });

                if (executeProperties.getExecuteSql()) {
                    insert.insert(je);
                }
            });
        } catch (RuntimeException e) {
            event.setFailed(e);
        }

        event.getFailed().ifPresent(t -> {throw t;});
    }
}
