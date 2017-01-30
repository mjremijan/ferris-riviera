package org.ferris.riviera.console.jar;

import java.util.jar.JarFile;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarFinderEvent {

    public static final int FIND = 100;
    public static final int VIEW = 110;

    protected JarFile jarFile;

    void setJarFile(JarFile jarFile) {
        this.jarFile = jarFile;
    }

}
