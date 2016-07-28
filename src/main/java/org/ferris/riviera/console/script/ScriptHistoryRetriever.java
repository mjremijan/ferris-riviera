package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.Statement;
import java.util.StringJoiner;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE;
import org.jboss.weld.experimental.Priority;

;

public class ScriptHistoryRetriever {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    protected void retrieveScriptHistory(
            @Observes @Priority(RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE) ScriptRetrievalEvent event
    ) {
        Connection conn
                = handler.getConnection();

        StringBuilder sp = new StringBuilder();
        sp.append(" SELECT ");
        sp.append(" MAJOR, FEATURE, BUG, BUILD, NAME, APPLIED_ON ");
        sp.append(" FROM ");
        sp.append(" DDL_SCRIPT_HISTORY ");
        sp.append(" ORDER BY ");
        sp.append(" MAJOR, FEATURE, BUG, BUILD ASC ");
        
//        try (Statement stmt = conn.createStatement();) {
//            log.info(String.format("Creating script table%n%s", sql));
//            event.setTableCreatedSuccessfully(
//                    stmt.execute(sql)
//            );
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
