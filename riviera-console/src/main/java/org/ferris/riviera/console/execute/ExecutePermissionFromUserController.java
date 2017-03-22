package org.ferris.riviera.console.execute;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.execute.ExecuteEvent.GET_PERMISSION_FROM_USER;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Normal;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecutePermissionFromUserController {

    @Inject
    protected Logger log;

    @Inject
    protected ExecutePermissionFromUserPage page;

    @Inject
    @Normal
    protected Event<ExitEvent> exitEvent;

    public void observeGetPermissionFromUser(
        @Observes @Priority(GET_PERMISSION_FROM_USER) ExecuteEvent event
    ) {
        log.info("ENTER");

        page.askPermissionFromUser(event);

        if (!event.isApproved().get()) {
            log.info("Permission not given...firing normal ExitEvent");
            exitEvent.fire(new ExitEvent());
        } else {
            page.startExecutingScripts();
        }
    }
}
