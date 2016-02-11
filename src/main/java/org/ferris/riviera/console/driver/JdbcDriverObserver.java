package org.ferris.riviera.console.driver;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverObserver {

    @Inject
    protected Logger log;
    
    @Inject
    protected JdbcDriverPage page;
        
    @Inject
    protected JdbcDriverHandler handler;
    
    protected void view(@Observes @Priority(JdbcDriverEvent.VIEW) JdbcDriverEvent evnt) {
        log.info("ENTER");
        page.view(handler.getJdbcDrivers());
    }
}
