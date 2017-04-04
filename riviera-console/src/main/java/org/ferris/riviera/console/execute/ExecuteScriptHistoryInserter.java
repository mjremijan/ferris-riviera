package org.ferris.riviera.console.execute;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import org.ferris.riviera.console.jar.JarEntry;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteScriptHistoryInserter {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    protected PreparedStatement stmt;

    @PostConstruct
    protected void postConstruct() {
        log.info("ENTER ExecuteScriptHistoryInserter#postConstruct");
        try {
            StringBuilder sp = new StringBuilder();
            sp.append(" INSERT INTO SCRIPT_HISTORY ");
            sp.append(" ( ");
            sp.append("   RELEASE_VERSION ");
            sp.append(" , RELEASE_TITLE ");
            sp.append(" , MAJOR ");
            sp.append(" , FEATURE ");
            sp.append(" , BUG ");
            sp.append(" , BUILD ");
            sp.append(" , FILE_NAME ");
            sp.append(" , FILE_DESCRIPTION ");
            sp.append(" , APPLIED_ON ");
            sp.append(" ) ");
            sp.append(" VALUES ");
            sp.append(" ( ");
            sp.append("   ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" , ? ");
            sp.append(" ) ");

            stmt = handler.getConnection().prepareStatement(sp.toString());
        } catch (Exception e) {
            throw new RuntimeException(
                String.format("Exception preparing PreparedStatement object"),
                 e
            );
        }
    }

    @ExecuteSkip
    protected void insert(JarEntry jarEntry) {
        log.info("ENTER");
        try {
            log.info(String.format("Saving history for %s", jarEntry.getVersion()));

            log.info(String.format("getReleaseVersion %s", jarEntry.getReleaseVersion()));
            stmt.setString(1, jarEntry.getReleaseVersion());

            log.info(String.format("getReleaseTitle %s", jarEntry.getReleaseTitle()));
            stmt.setString(2, jarEntry.getReleaseTitle());

            log.info(String.format("getMajor %d", jarEntry.getMajor()));
            stmt.setInt(3, jarEntry.getMajor());

            log.info(String.format("getFeature %d", jarEntry.getFeature()));
            stmt.setInt(4, jarEntry.getFeature());

            log.info(String.format("getBug %d", jarEntry.getBug()));
            stmt.setInt(5, jarEntry.getBug());

            log.info(String.format("getBuild %d", jarEntry.getBuild()));
            stmt.setInt(6, jarEntry.getBuild());

            log.info(String.format("getFileName %s", jarEntry.getFileName()));
            stmt.setString(7, jarEntry.getFileName());

            log.info(String.format("getFileDescription %s", jarEntry.getFileDescription()));
            stmt.setString(8, jarEntry.getFileDescription());

            Timestamp ts = new Timestamp(System.currentTimeMillis());
            log.info(String.format("appliedOn %s", String.valueOf(ts)));
            stmt.setTimestamp(9, ts);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                String.format("Exception inserting into script history table %s", jarEntry.getVersion()),
                 e
            );
        }
    }
}
