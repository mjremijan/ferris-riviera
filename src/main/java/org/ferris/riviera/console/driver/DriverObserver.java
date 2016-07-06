package org.ferris.riviera.console.driver;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.driver.DriverEvent.VIEW;
import org.jboss.weld.experimental.Priority;

/**
 * Shows the application welcome page to the user.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DriverObserver {

    @Inject
    protected Logger log;
    
    

//    @Inject
//    protected WelcomePage page;
    
    


    public void view(
        @Observes @Priority(VIEW) DriverEvent event
    ) {
        log.info("DriverEvent startup configuration observer");
        page.view();
    }
}
