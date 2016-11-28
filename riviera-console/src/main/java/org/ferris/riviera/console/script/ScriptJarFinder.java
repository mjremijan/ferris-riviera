package org.ferris.riviera.console.script;

import java.nio.file.Paths;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.FIND_SCRIPT_JAR_PATH;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptJarFinder {

    @Inject
    protected Logger log;

    @Inject
    protected ScriptJarFile scriptJarFile;

    protected void findScriptJarPath(
            @Observes @Priority(FIND_SCRIPT_JAR_PATH) ScriptRetrievalEvent event
    ) {
        // Something quarky about a {{JarFile}} object is the getName()
        // method returns an absolute path.  So let's wrap it in Path
        // so things work a bit more as expected.
        event.setScriptJarFile(
            Paths.get(scriptJarFile.getName())
        );
    }
}
