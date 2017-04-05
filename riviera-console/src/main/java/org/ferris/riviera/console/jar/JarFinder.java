package org.ferris.riviera.console.jar;

import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.jar.JarFinderEvent.FIND;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarFinder {

    @Inject
    protected Logger log;

    @Inject
    protected JarDirectory directory;

    /**
     * Find the jar file on the file system
     * @param event The event to process
     * @throws IOException Thrown if anything goes wrong reading the JAR file
     */
    protected void findTheJarFile(
        @Observes @Priority(FIND) JarFinderEvent event
    ) throws IOException {
        log.info("ENTER");

        // List all JAR files in the directory
        File[] jars
            = directory.listFiles(
                f -> f.isFile() && f.getName().toLowerCase().endsWith(".jar")
            );

        // If null or empty, throw exception
        if (isNull(jars) || jars.length == 0) {
            throw new RuntimeException(
                format(
                    "No script JAR files in directory \"%s\"", directory.getAbsolutePath())
            );
        }

        // If more than 1, throw exception
        if (jars.length > 1) {
            throw new RuntimeException(
                format(
                    "Multiple JAR files in directory \"%s\"", directory.getAbsolutePath())
            );
        }

        // Otherwise, get the JAR file
        event.setJarFile(new JarFile(jars[0]));
    }
}
