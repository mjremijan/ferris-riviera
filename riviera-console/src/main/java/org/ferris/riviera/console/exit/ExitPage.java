package org.ferris.riviera.console.exit;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

public class ExitPage {

    @Inject
    protected Console console;

    @Inject
    protected Logger log;

    @Inject
    @Key("ExitPage.Heading")
    protected String heading;

    @Inject
    @Key("ExitPage.Goodbye")
    protected String goodbye;

    public void view() {
        log.info("Application exiting");
        console.h1(heading);
        console.p(goodbye);
    }

}
