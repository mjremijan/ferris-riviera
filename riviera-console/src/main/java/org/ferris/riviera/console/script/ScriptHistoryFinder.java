package org.ferris.riviera.console.script;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import org.ferris.riviera.console.connection.ConnectionProperties;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.FIND_SCRIPT_TABLE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptHistoryFinder {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ConnectionProperties connectionProperties;

    protected void findScriptTable(
            @Observes @Priority(FIND_SCRIPT_TABLE) ScriptRetrievalEvent event
    ) {
        try (
                Connection conn = handler.getConnection();) {
            // http://apache-database.10148.n7.nabble.com/DatabaseMetaData-getTables-resultset-empty-td105623.html
            String catalog
                    = connectionProperties.getCatalog();

            String schemaPattern
                    = connectionProperties.getSchemaPattern();

            String tableNamePattern
                    = connectionProperties.getNamePattern();

            String[] types
                    = connectionProperties.getTypes();

            try (
                    ResultSet rs = conn.getMetaData().getTables(catalog, schemaPattern, tableNamePattern, types);) {
                event.setTableThere(rs.next());
            }

            log.info(
                    String.format("The script table was%sfound!", (event.isTableThere() ? " " : " *NOT* "))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
