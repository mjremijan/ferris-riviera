package org.ferris.riviera.console.jar;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryFilter {
    @Inject
    protected Logger log;

    protected void filterJarEntries(
        @Observes @Priority(JarEntryFinderEvent.FILTER) JarEntryFinderEvent event
    ) {

    }


}
