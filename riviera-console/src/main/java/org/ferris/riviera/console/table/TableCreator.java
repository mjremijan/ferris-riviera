package org.ferris.riviera.console.table;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.Statement;
import java.util.StringJoiner;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.table.TableValidationEvent.CREATE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TableCreator {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    protected void createScriptTable(
        @Observes @Priority(CREATE) TableValidationEvent event
    ) {
        log.info("ENTER");

        if (event.wasFound()) {
            log.debug("Table already exists, do not create it again");
            event.setWasCreated(false);
            return;
        }

        String sql = new StringJoiner(",", "CREATE TABLE SCRIPT_HISTORY (", ")")
            .add("RELEASE_VERSION VARCHAR(8) NOT NULL")
            .add("RELEASE_TITLE VARCHAR(50)")
            .add("MAJOR INT NOT NULL")
            .add("FEATURE INT NOT NULL")
            .add("BUG INT NOT NULL")
            .add("BUILD INT NOT NULL")
            .add("FILE_DESCRIPTION VARCHAR(50)")
            .add("FILE_NAME VARCHAR(100) NOT NULL")
            .add("APPLIED_ON TIMESTAMP NOT NULL")
            .add(
                new StringJoiner(",", "PRIMARY KEY (", ")")
                    .add("MAJOR").add("FEATURE").add("BUG").add("BUILD")
                    .toString()
            )
            .toString();

        try (
            Connection conn = handler.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            log.info(format("Creating script table:%n%s", sql));
            event.setWasCreated(
                stmt.execute(sql)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
