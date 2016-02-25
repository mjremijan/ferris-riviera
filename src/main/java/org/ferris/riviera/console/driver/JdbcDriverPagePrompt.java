package org.ferris.riviera.console.driver;

import java.util.List;
import javax.inject.Inject;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverPagePrompt {

    @Inject
    protected Console console;
    
    @Inject @Key("JdbcDriverPage.PromptLabel")
    protected String promptLabel;
    
    @Inject @Key("JdbcDriverPage.PromptLastUsed")
    protected String promptLastUsed;
    
    @Inject @Key("JdbcDriverPage.PromptIndicator")
    protected String promptIndicator;    
    
    protected void view(List<JdbcDriver> drivers) {
        console.p(promptLabel);        
        if (drivers.stream().filter(d -> d.isLastUsed()).count() != 0) {
            console.p(promptLastUsed);
        }
        console.p(promptIndicator);
    }

}
