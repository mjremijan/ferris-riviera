package org.ferris.riviera.console.jar;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import static org.ferris.riviera.console.jar.JarEntryFinderEvent.VIEW;
import org.ferris.riviera.console.messages.Key;
import org.jboss.weld.experimental.Priority;

@Singleton
public class JarEntryPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    public void viewOfJarEntryProblems(
          @Observes @Priority(VIEW) JarEntryFinderEvent event
        , @Key("JarPage.Heading") String heading
        , @Key("JarPage.FileNameFormat") String fileNameFormat
        , @Key("JarPage.ScriptCountFormat") String scriptCountFormat
    ) {
        log.info("ENTER");
        if (event.getJarEntryProblems().isEmpty()) {
            return;
        }
        
        console.h1(heading);
        console.p(fileNameFormat, event.getJarFile().getFileName());
        console.p(scriptCountFormat, String.valueOf(event.getJarFile().getScriptCount()));
    }
}
