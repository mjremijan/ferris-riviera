package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE;
import org.jboss.weld.experimental.Priority;


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
        sp.append(" SCRIPT_HISTORY ");
        sp.append(" ORDER BY ");
        sp.append(" MAJOR, FEATURE, BUG, BUILD ASC ");

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sp.toString());
        ) {
            log.info(String.format("Select script history%n%s", sp.toString()));

            List<Script> scriptHistory
                = new LinkedList<>();

            while (rs.next()) {
                scriptHistory.add(
                    new Script(
                          rs.getInt("MAJOR")
                        , rs.getInt("FEATURE")
                        , rs.getInt("BUG")
                        , rs.getInt("BUILD")
                        , rs.getString("NAME")
                        , rs.getDate("APPLIED_ON")
                    )
                );
            }

            log.info(String.format(
                "Found %d scripts in database history", scriptHistory.size())
            );

            event.setScriptHistoryFromDatabase(
                new ScriptHistory(scriptHistory)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
