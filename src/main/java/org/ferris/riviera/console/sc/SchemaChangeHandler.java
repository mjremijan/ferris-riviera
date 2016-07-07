package org.ferris.riviera.console.sc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.sc.SchemaChangeRetrievalEvent.LOAD_CHANGES_FROM_DATABASE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class SchemaChangeHandler {
    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    protected void loadChangesFromDatabase(
        @Observes @Priority(LOAD_CHANGES_FROM_DATABASE) SchemaChangeRetrievalEvent event
    ) {
        Connection conn = handler.getConnection();
        try {
            Statement stmt
                = conn.createStatement();

            //stmt.execute("set schema app");

            // http://apache-database.10148.n7.nabble.com/DatabaseMetaData-getTables-resultset-empty-td105623.html
            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String[] types = null; //{"TABLE"};
            ResultSet tables
                = conn.getMetaData().getTables(catalog, schemaPattern, tableNamePattern, types);
            System.out.printf("TABLES%n");

            while (tables.next()) {
                System.out.printf(
                    "TABLE_TYPE=%s, TABLE_CAT=%s, TABLE_SCHEM=%s, TABLE_NAME=%s%n"
                    , tables.getString("TABLE_TYPE")
                    , tables.getString("TABLE_CAT")
                    , tables.getString("TABLE_SCHEM")
                    , tables.getString("TABLE_NAME")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
