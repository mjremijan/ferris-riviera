package org.ferris.riviera.console.script.jar;

import java.io.File;
import javax.enterprise.event.Observes;
import org.ferris.riviera.console.script.ScriptProcessingEvent;
import static org.ferris.riviera.console.script.ScriptProcessingEvent.FIND_JAR_FILE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptJarFileFinder {

    /**
     * Find a JAR file containing the scripts to be applied to the database.
     *
     * @param scriptsDirectory
     * @param event
     */
    protected void findScriptJarFile(
        ScriptsDirectory scriptsDirectory
      , @Observes @Priority(FIND_JAR_FILE) ScriptProcessingEvent event
    ) {
        ScriptJarFile scriptJarFile;

        File[] jars
            = scriptsDirectory.listFiles(
                f -> f.isFile() && f.getName().toLowerCase().endsWith(".jar")
            );

        if (jars == null || jars.length == 0) {
            throw new RuntimeException(
                String.format(
                    "No JAR files in directory \"%s\"", scriptsDirectory.getAbsolutePath())
            );
        }
        else
        if (jars.length > 1) {
            throw new RuntimeException(
                String.format(
                    "Multiple JAR files in directory \"%s\"", scriptsDirectory.getAbsolutePath())
            );
        }

        try {
            scriptJarFile = new ScriptJarFile(scriptsDirectory, jars[0].getName());
        } catch (Exception e) {
            throw new RuntimeException(
                String.format(
                    "ERROR creating new ScriptJar object with parameters \"%s\", \"%s\""
                    , String.valueOf(scriptsDirectory)
                    , String.valueOf(jars[0].getName())
                )
                , e
            );
        }

        event.setScriptJarFile(scriptJarFile);
    }
}
