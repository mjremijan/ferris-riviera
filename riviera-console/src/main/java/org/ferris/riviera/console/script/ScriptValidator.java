package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.VALIDATE_NEW_SCRIPTS_TO_APPLY;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptValidator {

    public void validate(
    @Observes @Priority(VALIDATE_NEW_SCRIPTS_TO_APPLY) ScriptRetrievalEvent event
    ) {
//        if (!fileVersion.startsWith(releaseVersion)) {
//            throw new IllegalArgumentException(
//                String.format(
//                    "The JarEntry \"%s\" is invalid because its file name \"%s\" starts with the version \"%s\" but the directory is \"%s\"", m.group(0), fileName, fileVersion, directoryVersion
//                )
//            );
//        }

//        release version 8
//        release title 50
//        description 50
//        filename 100
    }
}
