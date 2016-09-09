package org.ferris.riviera.console.script;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ScriptJarFile extends JarFile {

    public ScriptJarFile(File parent, String child) throws IOException {
        super(new File(parent, child));
    }
}
