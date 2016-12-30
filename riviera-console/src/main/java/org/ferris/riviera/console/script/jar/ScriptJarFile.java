package org.ferris.riviera.console.script.jar;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptJarFile extends JarFile {

    public ScriptJarFile(File parent, String child) throws IOException {
        super(new File(parent, child));
    }
}
