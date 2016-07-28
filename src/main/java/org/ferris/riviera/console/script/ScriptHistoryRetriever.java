package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.Statement;
import java.util.StringJoiner;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.CREATE_SCRIPT_TABLE;
import org.jboss.weld.experimental.Priority;

;

public class ScriptHistoryRetriever {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    protected void createScriptTable(
            @Observes @Priority(CREATE_SCRIPT_TABLE) ScriptRetrievalEvent event
    ) {
        if (event.isTableThere()) {
            return;
        }

        Connection conn
                = handler.getConnection();

        String sql = new StringJoiner(",", "CREATE TABLE DDL_SCRIPT_HISTORY (", ")")
                .add("MAJOR INT NOT NULL")
                .add("FEATURE INT NOT NULL")
                .add("BUG INT NOT NULL")
                .add("BUILD INT NOT NULL")
                .add("NAME VARCHAR(100) NOT NULL")
                .add("APPLIED_ON  TIMESTAMP NOT NULL")
                .add(
                        new StringJoiner(",", "PRIMARY KEY (", ")")
                        .add("MAJOR").add("FEATURE").add("BUG").add("BUILD")
                        .toString()
                )
                .toString();

        try (Statement stmt = conn.createStatement();) {
            log.info(String.format("Creating script table%n%s", sql));
            event.setTableCreatedSuccessfully(
                    stmt.execute(sql)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
