package org.ferris.riviera.console.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.event.Event;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionValidationEvent;
import org.ferris.riviera.console.execute.ExecuteEvent;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Normal;
import org.ferris.riviera.console.history.HistoryFinderEvent;
import org.ferris.riviera.console.jar.JarEntryFinderEvent;
import org.ferris.riviera.console.jar.JarEntryValidationEvent;
import org.ferris.riviera.console.jar.JarFinderEvent;
import org.ferris.riviera.console.table.TableFinderEvent;
import org.ferris.riviera.console.welcome.WelcomeEvent;

/**
 * Entry point for the application...this is where it all starts.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {

    public static void main(String[] args) {
//        CDI<Object> cdi = CDI.getCDIProvider().initialize();
//        Main main
//                = cdi.select(Main.class).get();
//        main.main(Arrays.asList(args));
        SeContainer container
            = SeContainerInitializer.newInstance().initialize();
        Main main
            = container.select(Main.class).get();
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
    protected Event<TableFinderEvent> tableFinderEvent;

    @Inject
    protected Event<HistoryFinderEvent> historyFinderEvent;

    @Inject
    protected Event<JarFinderEvent> jarFinderEvent;

    @Inject
    protected Event<JarEntryFinderEvent> jarEntryFinderEvent;

    @Inject
    protected Event<JarEntryValidationEvent> jarEntryValidationEvent;

    @Inject
    protected Event<ExecuteEvent> executeEvent;

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

        log.info("Firing TableFinderEvent");
        tableFinderEvent.fire(new TableFinderEvent());

        log.info("Firing HistoryFinderEvent");
        HistoryFinderEvent hfe = new HistoryFinderEvent();
        historyFinderEvent.fire(hfe);

        log.info("Firing JarFinderEvent");
        JarFinderEvent jfe = new JarFinderEvent();
        jarFinderEvent.fire(jfe);

        log.info("Firing JarEntryFinderEvent");
        JarEntryFinderEvent jefe = new JarEntryFinderEvent(
              jfe.getJarFile()
            , hfe.getHistory().stream().map(h -> h.toVersionString()).collect(Collectors.toList())
        );
        jarEntryFinderEvent.fire(jefe);

        log.info("Firing JarEntryValidationEvent");
        JarEntryValidationEvent jeve = new JarEntryValidationEvent(
            jefe.getJarEntries()
        );
        jarEntryValidationEvent.fire(jeve);

        log.info("Firing ExecuteEvent");
        ExecuteEvent ee = new ExecuteEvent(jfe.getJarFile(), jefe.getJarEntries());
        executeEvent.fire(ee);


        log.info("Firing normal ExitEvent");
        exitEvent.fire(new ExitEvent());
    }
}
