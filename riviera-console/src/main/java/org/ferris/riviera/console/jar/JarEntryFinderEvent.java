package org.ferris.riviera.console.jar;

import java.util.List;
import org.ferris.riviera.console.jar.JarFile;
import org.ferris.riviera.console.jar.JarFile;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryFinderEvent {
    public static final int FILTER = 100;
    public static final int SORT = 110;

    protected JarFile jarFile;
    protected List<String> versionsInTheDatabase;
    protected List<JarEntry> jarEntries;

    public JarEntryFinderEvent(JarFile jarFile, List<String> versionsInTheDatabase) {
        this.jarFile = jarFile;
        this.versionsInTheDatabase = versionsInTheDatabase;
    }

    public JarFile getJarFile() {
        return jarFile;
    }

    public List<String> getVersionsInTheDatabase() {
        return versionsInTheDatabase;
    }

    void setJarEntries(List<JarEntry> jarEntries) {
        this.jarEntries = jarEntries;
    }

    public List<JarEntry> getJarEntries() {
        return this.jarEntries;
    }
}
