package org.ferris.riviera.console.jar;

import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.jar.JarEntryFinderEvent.VALIDATE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryValidator {

    @Inject
    protected Logger log;

    protected void filterJarEntries(
        @Observes @Priority(VALIDATE) JarEntryFinderEvent event
    ) {
        log.info("ENTER");
        List<JarEntry> entries
            = event.getJarEntries();

    }
}
