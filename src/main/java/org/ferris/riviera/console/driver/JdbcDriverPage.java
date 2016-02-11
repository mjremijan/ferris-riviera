package org.ferris.riviera.console.driver;

import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverPage {

    @Inject
    protected Logger log;
    
    @Inject
    protected Console console;    
    
    @Inject @Key("DriverPage.Heading")
    protected String heading;
    
    public void view(List<JdbcDriver> drivers) {
        console.h1(heading);
        drivers.forEach(d -> console.p(d.getImplementationClass().getName()));
    }
}
