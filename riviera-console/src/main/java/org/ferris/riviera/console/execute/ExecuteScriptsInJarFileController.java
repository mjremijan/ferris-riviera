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
    protected ExecuteScriptHistoryInsert history;

    @Inject
    protected ExecuteScriptSqlStatement sql;


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

                event.getJarFile().getJarEntryStatements(je).stream().forEach(sql -> {
                    page.showSQLStatementThatsBeingExecuted(sql);
                    this.sql.execute(sql);
                });

                history.insert(je);
            });
        } catch (RuntimeException e) {
            event.setFailed(e);
        }

        event.getFailed().ifPresent(t -> {throw t;});
    }
}
