package org.ferris.riviera.console.driver;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DriverObserver {

    @Inject
    protected Logger log;
    
    @Inject
    protected DriverPage page;
    
    protected void view(@Observes @Priority(DriverEvent.VIEW) DriverEvent evnt) {
        log.info("ENTER");
        page.view();
    }
}
