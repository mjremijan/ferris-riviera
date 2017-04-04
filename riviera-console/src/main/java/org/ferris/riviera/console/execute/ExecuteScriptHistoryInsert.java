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
public class ExecuteScriptHistoryInsert {

    @Inject
    protected Logger log;

    protected PreparedStatement stmt;

    @PostConstruct
    protected void postConstruct(ConnectionHandler handler) {
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
                  String.format("Exception preparing PreparedStatement object")
                , e
            );
        }
    }

    @ExecutionSkip
    public void insert(JarEntry jarEntry) {
        log.info("ENTER");
        try {
            log.info(String.format("Saving history for %s",jarEntry.getVersion()));

            stmt.setString(1, jarEntry.getReleaseVersion());
            stmt.setString(2, jarEntry.getReleaseTitle());
            stmt.setInt(3, jarEntry.getMajor());
            stmt.setInt(4, jarEntry.getFeature());
            stmt.setInt(5, jarEntry.getBug());
            stmt.setInt(6, jarEntry.getBuild());
            stmt.setString(7, jarEntry.getFileDescription());
            stmt.setString(8, jarEntry.getFileName());
            stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                  String.format("Exception inserting into script history table %s", jarEntry.getVersion())
                , e
            );
        }
    }
}
