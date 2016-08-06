package org.ferris.riviera.console.script;

import java.io.File;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptJarProducer {

    private ScriptJar scriptJar;

    @Produces
    public ScriptJar produceScriptJarFile(ScriptAppDirectory scriptAppDirectory) {
        if (scriptJar == null)
        {
            File[] jars
                = scriptAppDirectory.listFiles(
                    f -> f.isFile() && f.getName().toLowerCase().endsWith(".jar")
                );

            if (jars == null || jars.length == 0) {
                throw new RuntimeException(
                    String.format(
                        "No JAR files in directory \"%s\"", scriptAppDirectory.getAbsolutePath())
                );
            } else
            if (jars.length > 1) {
                throw new RuntimeException(
                    String.format(
                        "Multiple JAR files in directory \"%s\"", scriptAppDirectory.getAbsolutePath())
                );
            }

            scriptJar
                = new ScriptJar(scriptAppDirectory, jars[0].getName());
        }

        return scriptJar;
    }
}
