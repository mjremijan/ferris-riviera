package org.ferris.riviera.console.jar;

import java.util.Collections;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.jar.JarEntryFinderEvent.SORT;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntrySorter {

    @Inject
    protected Logger log;

    protected void sortJarEntriesByFileVersionNumber(
        @Observes @Priority(SORT) JarEntryFinderEvent event
    ) {
        log.info("ENTER");
        Collections.sort(event.getJarEntries());
    }
}
