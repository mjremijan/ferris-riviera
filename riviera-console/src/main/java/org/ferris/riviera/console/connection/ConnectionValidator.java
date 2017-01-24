package org.ferris.riviera.console.connection;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.connection.ConnectionValidationEvent.VALIDATE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionValidator {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ConnectionProperties properties;


    /**
     *
     */
    protected void validateDatabaseConnection(
        @Observes @Priority(VALIDATE) ConnectionValidationEvent event
    ) {
        try (
            Connection conn = handler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(properties.getValidationSql())
        ) {
            if (rs.next()) {
                event.setValidationSql(properties.getValidationSql());
                event.setValidationResult(rs.getString(1));
            } else {
                throw new SQLException(
                    format("No result set returned for validation query \"%s\""
                    , properties.getValidationSql())
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
