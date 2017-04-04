package org.ferris.riviera.console.execute;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.execute.ExecuteEvent.EXECUTE_SHORT_CIRCUIT;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Normal;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteShortCircuitController {

    @Inject
    protected Logger log;

    @Inject
    @Normal
    protected Event<ExitEvent> exitEvent;


    public void observeExecuteShortCircuit(
        @Observes @Priority(EXECUTE_SHORT_CIRCUIT) ExecuteEvent event
    ) {
        log.info("ENTER");

        if (event.getJarEntries().isEmpty()) {
            exitEvent.fire(new ExitEvent());
        }
    }
}
