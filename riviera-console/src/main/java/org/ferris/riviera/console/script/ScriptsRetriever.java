package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptProcessingEvent.RETRIEVE_SCRIPTS_FROM_DATABASE;
import static org.ferris.riviera.console.script.ScriptProcessingEvent.RETRIEVE_SCRIPTS_FROM_JAR;
import org.ferris.riviera.console.script.jar.ScriptJarPattern;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ScriptsRetriever {

    @Inject
    protected Logger log;

    @Inject
    protected ScriptBuilder builder;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ScriptJarPattern pattern;

    protected void retrieveScriptsFromDatabaseOrderedAscending(
        @Observes @Priority(RETRIEVE_SCRIPTS_FROM_DATABASE) ScriptProcessingEvent event
    ) {
        log.info("Retrieving scripts from database");

        Connection conn
            = handler.getConnection();

        StringBuilder sp = new StringBuilder();
        sp.append(" SELECT ");
        sp.append("   RELEASE_VERSION ");
        sp.append(" , RELEASE_TITLE ");
        sp.append(" , MAJOR ");
        sp.append(" , FEATURE ");
        sp.append(" , BUG ");
        sp.append(" , BUILD ");
        sp.append(" , FILE_NAME ");
        sp.append(" , FILE_DESCRIPTION ");
        sp.append(" , APPLIED_ON ");
        sp.append(" FROM ");
        sp.append(" SCRIPT_HISTORY ");
        sp.append(" ORDER BY ");
        sp.append(" MAJOR, FEATURE, BUG, BUILD ASC ");

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sp.toString());)
        {
            log.info(String.format("Select script history%n%s", sp.toString()));
            List<Script> scripts
                = new LinkedList<>();

            while (rs.next()) {
                scripts.add(
                    builder.setResultSet(rs).build()
                );
            }

            log.info(String.format(
                "Found %d scripts in database", scripts.size())
            );

            event.setScriptsFromDatabase(
                new Scripts(scripts)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void retrieveScriptsFromJarOrderedAscending(
        @Observes @Priority(RETRIEVE_SCRIPTS_FROM_JAR) ScriptProcessingEvent event
    ) {
        log.info("Retrieving scripts from jar");
        try (JarFile jar = event.getScriptJarFile()) {
            List<Script> scripts = jar.stream()
                .filter(j -> j.isDirectory() == false)
                .map(j -> pattern.matcher(j.getName()))
                .filter(m -> m.matches())
                .map(m -> builder.setMatcher(m).build())
                .sorted()
                .collect(Collectors.toList());

            log.info(String.format(
                "Found %d scripts in JAR", scripts.size())
            );

            event.setScriptsFromJar(
                new Scripts(scripts)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
