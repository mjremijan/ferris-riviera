package org.ferris.riviera.console.jar;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryFinderEvent {
    public static final int FILTER = 100;
    public static final int VALIDATE = 100;
    public static final int VIEW = 100;

    protected JarFile jarFile;
    protected List<String> versionsInTheDatabase;
    protected List<JarEntry> jarEntries;
    protected Map<JarEntry, Set<ConstraintViolation<JarEntry>>> jarEntryProblems;

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

    void setJarEntryProblems(Map<JarEntry, Set<ConstraintViolation<JarEntry>>> jarEntryProblems) {
        this.jarEntryProblems = jarEntryProblems;
    }

    Map<JarEntry, Set<ConstraintViolation<JarEntry>>> getJarEntryProblems() {
        return jarEntryProblems;
    }
}
