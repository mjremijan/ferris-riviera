package org.ferris.riviera.console.execute;

import java.util.List;
import java.util.Optional;
import org.ferris.riviera.console.jar.JarEntry;
import org.ferris.riviera.console.jar.JarFile;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExecuteEvent {
    public static final int GET_PERMISSION_FROM_USER = 600;

    public static final int START_TRANSACTION = 700;

    public static final int EXECUTE_SCRIPTS_IN_JAR_FILE = 800;

    public static final int INSERT_SCRIPT_HISTORY = 800;

    public static final int COMMIT_TRANSACTION = 900;

    public static final int ROLLBACK_TRANSACTION = 1000;

    protected Optional<Boolean> approved;
    protected JarFile jarFile;
    protected List<JarEntry> jarEntries;
    protected Optional<RuntimeException> failed;

    public ExecuteEvent(JarFile jarFile, List<JarEntry> jarEntries) {
        this.approved = Optional.empty();
        this.failed = Optional.empty();
        this.jarFile = jarFile;
        this.jarEntries = jarEntries;
    }

    public Optional<Boolean> isApproved() {
        return approved;
    }

    public void setApproved(Boolean b) {
        approved = Optional.of(b);
    }

    public void setFailed(RuntimeException failed) {
        this.failed = Optional.of(failed);
    }

    public Optional<RuntimeException> getFailed() {
        return failed;
    }

    public JarFile getJarFile() {
        return jarFile;
    }

    public List<JarEntry> getJarEntries() {
        return jarEntries;
    }


}
