package org.ferris.riviera.console.applychanges;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ApplyChangesPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject @Key("ApplyChangesPage.YesNo")
    protected String yesNo;

    public void viewPromptUserForYesNo(
        @Observes @Priority(ApplyChangesEvent.GO_NO_GO) ApplyChangesEvent event
    ) {
        log.info("ENTER");

        // Display the prompt
        console.p(yesNo);

        // Get user input
    }
}
