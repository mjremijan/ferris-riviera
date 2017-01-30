package org.ferris.riviera.console.main;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionValidationEvent;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Normal;
import org.ferris.riviera.console.history.HistoryFinderEvent;
import org.ferris.riviera.console.jar.JarFinderEvent;
import org.ferris.riviera.console.table.TableValidationEvent;
import org.ferris.riviera.console.welcome.WelcomeEvent;

/**
 * Entry point for the application...this is where it all starts.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {

    public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        Main main
                = cdi.select(Main.class).get();
        main.main(Arrays.asList(args));
    }

    @Inject
    protected Logger log;

    @Inject
    protected Event<MainEvent> mainEvent;

    @Inject
    protected Event<WelcomeEvent> welcomeEvent;

    @Inject
    protected Event<ConnectionValidationEvent> connectionValidationEvent;

    @Inject
    protected Event<TableValidationEvent> tableValidationEvent;

    @Inject
    protected Event<HistoryFinderEvent> historyFinderEvent;

    @Inject
    protected Event<JarFinderEvent> jarFinderEvent;

    @Inject
    @Normal
    protected Event<ExitEvent> exitEvent;

    protected void main(List<String> args) {
        log.info("Riviera application has started");

        log.info("Firing MainEvent");
        mainEvent.fire(new MainEvent());

        log.info("Firing WelcomeEvent");
        welcomeEvent.fire(new WelcomeEvent());

        log.info("Firing ConnectionValidationEvent");
        connectionValidationEvent.fire(new ConnectionValidationEvent());

        log.info("Firing TableValidationEvent");
        tableValidationEvent.fire(new TableValidationEvent());

        log.info("Firing HistoryFinderEvent");
        historyFinderEvent.fire(new HistoryFinderEvent());

        log.info("Firing JarValidationEvent");
        jarFinderEvent.fire(new JarFinderEvent());

        log.info("Firing normal ExitEvent");
        exitEvent.fire(new ExitEvent());
    }
}
