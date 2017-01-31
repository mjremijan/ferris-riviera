package org.ferris.riviera.console.jar;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarFinderEvent {

    public static final int FIND = 100;
    public static final int VIEW = 120;

    private JarFile jarFile;

    void setJarFile(JarFile jarFile) {
        this.jarFile = jarFile;
    }
    JarFile getJarFile() {
        return this.jarFile;
    }
}
