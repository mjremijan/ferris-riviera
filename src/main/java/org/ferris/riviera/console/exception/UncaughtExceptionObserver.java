package org.ferris.riviera.console.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Abnormal;
import org.ferris.riviera.console.lang.ThreadTool;
import org.ferris.riviera.console.main.MainEvent;
import static org.ferris.riviera.console.main.MainEvent.EXCEPTION;
import org.ferris.riviera.console.welcome.WelcomeEvent;
import org.jboss.weld.experimental.Priority;

/**
 * This observer is configured during application {@link WelcomeEvent}
 * to catch any uncaught exceptions, display the stack trace, then exit
 * the application abnormally.
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionObserver implements UncaughtExceptionHandler {

    @Inject
    protected Logger log;

    @Inject
    protected ThreadTool threadTool;

    @Inject
    protected UncaughtExceptionPage uncaughtExceptionPage;

    @Inject @Abnormal
    protected Event<ExitEvent> exitEvent;

    public void observes(
        @Observes @Priority(EXCEPTION) MainEvent event
    ) {
        log.info("UncaughtExceptionObserver startup configuration observer");
        threadTool.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        uncaughtExceptionPage.view(e);
        exitEvent.fire(new ExitEvent());
    }
}
