package org.ferris.riviera.console.execute;

import java.sql.SQLException;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import static org.ferris.riviera.console.execute.ExecuteEvent.COMMIT_TRANSACTION;
import static org.ferris.riviera.console.execute.ExecuteEvent.ROLLBACK_TRANSACTION;
import static org.ferris.riviera.console.execute.ExecuteEvent.START_TRANSACTION;

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

    @ExecuteSkip
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

        page.showThatTransactionHasStarted();
    }


    @ExecuteSkip
    public void observeCommitTransaction(
        @Observes @Priority(COMMIT_TRANSACTION) ExecuteEvent event
    ) {
        log.info("ENTER");
        try {
            if (!event.getFailed().isPresent()) {
                page.showThatTransactionIsCommitting();
                handler.getConnection().commit();
            }
        } catch (SQLException e) {
            event.setFailed(
                new RuntimeException(
                  "Unable to commit the transaction"
                , e)
            );
        }
    }


    @ExecuteSkip
    public void observeRollbackTransaction(
        @Observes @Priority(ROLLBACK_TRANSACTION) ExecuteEvent event
    ) {
        log.info("ENTER");
        try {
            if (event.getFailed().isPresent()) {
                page.showThatTransactionIsRollingBack();
                handler.getConnection().rollback();
                throw event.getFailed().get();
            }
        } catch (SQLException e) {
            throw
                new RuntimeException(
                  "Unable to rollback the transaction"
                , e
            );
        }
    }
}
