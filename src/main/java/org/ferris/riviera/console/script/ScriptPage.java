package org.ferris.riviera.console.script;

import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPT_HISTORY; 
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import org.jboss.weld.experimental.Priority;

public class ScriptPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("ScriptPage.Heading")
    protected String heading;
    
    @Inject
    @Key("ScriptPage.Created")
    protected String created;
    
    @Inject
    @Key("ScriptPage.Found")
    protected String found;
    
    public void view(
    		@Observes @Priority(SHOW_SCRIPT_HISTORY) ScriptRetrievalEvent event
    ) {    	
    	console.h1(heading);
    	
    	if (event.isTableThere()) {
    		console.p(found);
    	} else {
    		console.p(created);
    	}
    }
}
