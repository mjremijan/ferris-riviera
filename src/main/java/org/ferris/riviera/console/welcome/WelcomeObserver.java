package org.ferris.riviera.console.welcome;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.jboss.weld.experimental.Priority;
import static org.ferris.riviera.console.welcome.WelcomeEvent.VIEW;

/**
 * Shows the application welcome page to the user.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class WelcomeObserver {

    @Inject
    protected Logger log;

    @Inject
    protected WelcomePage page;

    public void observes(
        @Observes @Priority(VIEW) WelcomeEvent event
    ) {
        log.info("WelcomeObserver startup configuration observer");
        page.view();
    }
}
