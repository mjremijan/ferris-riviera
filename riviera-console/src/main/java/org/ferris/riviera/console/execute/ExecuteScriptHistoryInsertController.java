package org.ferris.riviera.console.execute;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.execute.ExecuteEvent.INSERT_SCRIPT_HISTORY;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteScriptHistoryInsertController {

    @Inject
    protected Logger log;

    @Inject
    protected ExecuteScriptHistoryInserter inserter;

    protected void observeInsertScriptHistory(
        @Observes @Priority(INSERT_SCRIPT_HISTORY) ExecuteEvent event
    ) {
        log.info("ENTER");
        try {
            event.getJarEntries().stream().forEach(je -> {
                inserter.insert(je);
            });
        } catch (RuntimeException e) {
            event.setFailed(e);
        }
    }
}
