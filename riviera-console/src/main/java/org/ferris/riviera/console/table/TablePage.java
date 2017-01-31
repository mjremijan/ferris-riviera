package org.ferris.riviera.console.table;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import static org.ferris.riviera.console.table.TableFinderEvent.VIEW;
import org.jboss.weld.experimental.Priority;

@Singleton
public class TablePage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    public void viewFromDatabase(
          @Observes @Priority(VIEW) TableFinderEvent event
        , @Key("TablePage.Heading") String heading
        , @Key("TablePage.Created") String created
        , @Key("TablePage.Found") String found
    ) {
        log.info("ENTER");

        console.h1(heading);
        console.p(event.wasFound() ? found : created);
    }
}
