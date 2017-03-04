package org.ferris.riviera.console.history;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringJoiner;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.history.HistoryFinderEvent.FIND;
import org.jboss.weld.experimental.Priority;

@Singleton
public class HistoryFinder {

    @Inject
    protected Logger log;

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

        try (
            Connection conn = handler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            HistoryList list
                = new HistoryList();

            while (rs.next()) {
                list.add(
                    new History(
                        rs.getString("RELEASE_VERSION")
                      , rs.getString("RELEASE_TITLE")
                      , rs.getInt("MAJOR")
                      , rs.getInt("FEATURE")
                      , rs.getInt("BUG")
                      , rs.getInt("BUILD")
                      , rs.getString("FILE_NAME")
                      , rs.getString("FILE_DESCRIPTION")
                      , rs.getTimestamp("APPLIED_ON")
                  )
                );
            }

            log.info(format(
                "Found %d scripts in database", list.size())
            );

            event.setHistory(list);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

