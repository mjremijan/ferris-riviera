package org.ferris.riviera.console.welcome;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 * View of the welcome page
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class WelcomeView {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("WelcomePage.Title")
    protected String title;
    
    @Inject
    @Key("WelcomePage.Version")
    protected String version;

    /**
     * Show the user the welcome page
     */
    public void view() {
        console.title(title);
        console.p(version);
    }
}
