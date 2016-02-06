package org.ferris.riviera.console.main;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Normal;
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
    protected Event<WelcomeEvent> startupEvent;
    
    @Inject @Normal
    protected Event<ExitEvent> exitEvent;
    
    protected void main(List<String> args) {
        log.info("Riviera application has started");

        log.debug("Firing StartupEvent"); 
        startupEvent.fire(new WelcomeEvent());
        
        log.debug("Firing normal ExitEvent"); 
        exitEvent.fire(new ExitEvent());
    }
}
