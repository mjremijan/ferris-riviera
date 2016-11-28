package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPTS_FROM_DATABASE;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.RETRIEVE_SCRIPTS_FROM_JAR;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ScriptsRetriever {

    @Inject
    protected Logger log;

    @Inject
    protected ScriptBuilder builder;

    @Inject
    protected ConnectionHandler handler;

    private Pattern p;

    @PostConstruct
    protected void postConstruct() {
        String dirRegex
            = "((\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?)";
        String fileRegex
            = "((\\d+.{1}\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?\\.sql)";
        p = Pattern.compile(dirRegex + "/" + fileRegex);
    }

    protected void retrieveScriptsFromDatabaseOrderedAscending(
        @Observes @Priority(RETRIEVE_SCRIPTS_FROM_DATABASE) ScriptRetrievalEvent event
    ) {
        log.info("Retrieving scripts from database");

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
        @Observes @Priority(RETRIEVE_SCRIPTS_FROM_JAR) ScriptRetrievalEvent event
    ) {
        log.info("Retrieving scripts from jar");
        try (JarFile jar = new JarFile(event.getScriptJarFile().toAbsolutePath().toString())) {
            List<Script> scripts = jar.stream()
                .filter(j -> j.isDirectory() == false)
                .map(j -> p.matcher(j.getName()))
                .filter(m -> m.matches())
                .map(m -> builder.setMatcher(m).build())
                .sorted()
                .collect(Collectors.toList());

//            Enumeration<JarEntry> jarEntries
//                = jar.entries();
//
//            List<Script> scripts
//                = new LinkedList<>();
//
//            while (jarEntries.hasMoreElements()) {
//                JarEntry je = jarEntries.nextElement();
//                Script sc = builder.setJarEntry(je).build();
//                if (sc != null) {
//                    scripts.add(sc);
//                }
//            }
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
