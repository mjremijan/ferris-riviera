package org.ferris.riviera.console.execute;

import java.sql.SQLException;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.execute.ExecuteEvent.START_TRANSACTION;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteTransactionController {

    @Inject
    protected Logger log;

    @Inject
    protected ConnectionHandler handler;

    @Inject
    protected ExecuteTransactionPage page;

    public void observeStartTransaction(
        @Observes @Priority(START_TRANSACTION) ExecuteEvent event
    ) {
        log.info("ENTER");
        try {
            handler.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(
                  "Unable to start a transaction with #setAutoCommit()"
                , e
            );
        }

        page.transactionStarted();
    }
}
