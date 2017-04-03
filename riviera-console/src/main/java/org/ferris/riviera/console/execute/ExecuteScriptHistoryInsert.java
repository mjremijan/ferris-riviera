package org.ferris.riviera.console.execute;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import static org.ferris.riviera.console.execute.ExecuteEvent.INSERT_INTO_SCRIPT_HISTORY;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExecuteScriptHistoryInsert {

    public void observeInsertIntoScriptHistory(
        @Observes
        @Priority(INSERT_INTO_SCRIPT_HISTORY) ExecuteEvent event
    ) {

    }
}
