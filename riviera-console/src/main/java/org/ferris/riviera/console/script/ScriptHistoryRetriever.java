package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPT_HISTORY_FROM_JAR;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ScriptHistoryRetriever {

    @Inject
    protected Logger log;

    @Inject
    protected ScriptBuilder builder;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ScriptJarFile scriptJarFile;

    protected void retrieveScriptHistoryFromDatabase(
        @Observes @Priority(RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE) ScriptRetrievalEvent event
    ) {
        log.info("Retrieving script history from database");

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
            ResultSet rs = stmt.executeQuery(sp.toString());) {
            log.info(String.format("Select script history%n%s", sp.toString()));

            List<Script> scriptHistory
                = new LinkedList<>();

            while (rs.next()) {
                scriptHistory.add(
                    builder.setResultSet(rs).build()
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

    protected void retrieveScriptHistoryFromJar(
        @Observes @Priority(RETRIEVE_SCRIPT_HISTORY_FROM_JAR) ScriptRetrievalEvent event
    ) {
        log.info("Retrieving script history from jar");

        Enumeration<JarEntry> jarEntries
            = scriptJarFile.entries();

        List<Script> scriptHistory
            = new LinkedList<>();

        while (jarEntries.hasMoreElements()) {
            JarEntry je = jarEntries.nextElement();
            Script sc = builder.setJarEntry(je).build();
            if (sc != null) {
                scriptHistory.add(sc);
            }
        }

        log.info(String.format(
            "Found %d scripts in JAR history", scriptHistory.size())
        );

        event.setScriptHistoryFromJar(
            new ScriptHistory(scriptHistory)
        );
    }
}
