package org.ferris.riviera.console.welcome;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.main.StartupEvent;
import static org.ferris.riviera.console.main.StartupEvent.WELCOME;
import org.jboss.weld.experimental.Priority;

/**
 * Shows the application welcome page to the user.
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class WelcomeObserver {

    @Inject
    protected Logger log;

    @Inject
    protected WelcomeView view;
    
    public void observes(
        @Observes @Priority(WELCOME) StartupEvent event
    ) {
        log.info("WelcomeObserver startup configuration observer");
        view.view();
    }
}
