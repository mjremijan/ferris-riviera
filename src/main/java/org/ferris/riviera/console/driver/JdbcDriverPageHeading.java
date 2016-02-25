package org.ferris.riviera.console.driver;

import javax.inject.Inject;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverPageHeading {

    @Inject
    protected Console console;

    @Inject @Key("JdbcDriverPage.Heading")
    protected String heading;

    protected void view() {
        console.h1(heading);
    }
}
