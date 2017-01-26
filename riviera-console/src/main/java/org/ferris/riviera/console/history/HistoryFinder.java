package org.ferris.riviera.console.history;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.history.HistoryFinderEvent.FIND;
import org.ferris.riviera.console.script.*;
import org.jboss.weld.experimental.Priority;

@Singleton
public class HistoryFinder {

    @Inject
    protected Logger log;

    @Inject
    protected ScriptBuilder builder;

    @Inject
    protected ConnectionHandler handler;

    protected void retrieveScriptsFromDatabaseOrderedAscending(
        @Observes @Priority(FIND) HistoryFinderEvent event
    ) {
        log.info("ENTER");

        String sql =
            new StringJoiner(",", " SELECT ", " FROM SCRIPT_HISTORY ")
                .add(" RELEASE_VERSION ")
                .add(" RELEASE_TITLE ")
                .add(" MAJOR ")
                .add(" FEATURE ")
                .add(" BUG ")
                .add(" BUILD ")
                .add(" FILE_NAME ")
                .add(" FILE_DESCRIPTION ")
                .add(" APPLIED_ON ")
            .toString()
            +
            new StringJoiner(",", " ORDER BY ", " ASC ")
                .add(" MAJOR ")
                .add(" FEATURE ")
                .add(" BUG ")
                .add(" BUILD ")
            .toString()
        ;
        log.debug(sql);

//        StringBuilder sp = new StringBuilder();
//        sp.append(" SELECT ");
//        sp.append("   RELEASE_VERSION ");
//        sp.append(" , RELEASE_TITLE ");
//        sp.append(" , MAJOR ");
//        sp.append(" , FEATURE ");
//        sp.append(" , BUG ");
//        sp.append(" , BUILD ");
//        sp.append(" , FILE_NAME ");
//        sp.append(" , FILE_DESCRIPTION ");
//        sp.append(" , APPLIED_ON ");
//        sp.append(" FROM ");
//        sp.append(" SCRIPT_HISTORY ");
//        sp.append(" ORDER BY ");
//        sp.append(" MAJOR, FEATURE, BUG, BUILD ASC ");

        try (
            Connection conn = handler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            List<Script> scripts
                = new LinkedList<>();

            while (rs.next()) {
                scripts.add(
                    builder.setResultSet(rs).build()
                );
            }

            log.info(format(
                "Found %d scripts in database", scripts.size())
            );

            event.setScriptsFromDatabase(
                new Scripts(scripts)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

