package org.ferris.riviera.console.jar;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryValidationEvent {
    public static final int VALIDATE = 100;
    public static final int VIEW = 110;

    protected List<JarEntry> jarEntries;
    protected Map<JarEntry, Set<ConstraintViolation<JarEntry>>> jarEntryConstraintViolations;

    public JarEntryValidationEvent(List<JarEntry> jarEntries) {
        this.jarEntries = jarEntries;
    }

    public List<JarEntry> getJarEntries() {
        return this.jarEntries;
    }

    void setJarEntryConstraintViolations(Map<JarEntry, Set<ConstraintViolation<JarEntry>>> jarEntryConstraintViolations) {
        this.jarEntryConstraintViolations = jarEntryConstraintViolations;
    }

    Map<JarEntry, Set<ConstraintViolation<JarEntry>>> getJarEntryConstraintViolations() {
        return jarEntryConstraintViolations;
    }
}
