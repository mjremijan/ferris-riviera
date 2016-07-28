package org.ferris.riviera.console.exception;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 * If an uncaught exception occurs, the {@link UncaughtExceptionObserver} will
 * handle it then send the exception to this page for displaying to the user.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("UncaughtExceptionPage.Heading")
    protected String heading;

    @Inject
    @Key("UncaughtExceptionPage.Opps")
    protected String opps;

    public void view(Throwable e) {
        log.fatal(e.getMessage(), e);

        console.h1(heading);
        console.p(opps);
        console.p(e);
    }
}
