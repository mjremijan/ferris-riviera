package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.FILTER_FOR_NEW_SCRIPTS_TO_APPLY;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ScriptFilter {

    @Inject
    protected Logger log;

    protected void filterForNewScriptsToApply(
        @Observes @Priority(FILTER_FOR_NEW_SCRIPTS_TO_APPLY) ScriptRetrievalEvent event
    ) {
        log.info("Filtering for new scripts to apply");

        Scripts
            fromJar = event.getScriptsFromJar();

        Scripts
            fromDatabase = event.getScriptsFromDatabase();

        Scripts
            toApply = new Scripts(fromJar).removeAll(fromDatabase);

        event.setScriptsToApply(toApply);
    }
}
