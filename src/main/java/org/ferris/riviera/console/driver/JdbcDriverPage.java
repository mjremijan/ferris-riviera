package org.ferris.riviera.console.driver;

import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverPage {

    @Inject
    protected Logger log;
    
    @Inject
    protected JdbcDriverPageHeading heading;
    
    @Inject
    protected JdbcDriverPageList list; 
    
    @Inject
    protected JdbcDriverPagePrompt prompt; 

    public void view(List<JdbcDriver> drivers) {
        heading.view();
        list.view(drivers);
        prompt.view(drivers);        
    }
}
