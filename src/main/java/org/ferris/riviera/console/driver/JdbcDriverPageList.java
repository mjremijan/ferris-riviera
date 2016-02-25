package org.ferris.riviera.console.driver;

import java.util.List;
import javax.inject.Inject;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverPageList {

    @Inject
    protected Console console;
    
    @Inject @Key("JdbcDriverPage.ListLabel")
    protected String label;
    
    @Inject @Key("JdbcDriverPage.ListLastUsed")
    protected String lastUsed;
    
    protected void view(List<JdbcDriver> drivers) {
        console.p(label);
        console.br();
        
        drivers.forEach(d -> console.li(
            String.format("%s%s"
                , d.getImplementationClass()
                , !d.isLastUsed() ? "" : String.format("  %s", lastUsed)
            )
        ));       
        console.br();
    }

}
