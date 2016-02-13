package org.ferris.riviera.console.driver;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriver {
    private String implementationClass;
    private boolean lastUsed;

    public JdbcDriver(String implementationClass, boolean lastUsed) {
        this.implementationClass = implementationClass;
        this.lastUsed = lastUsed;
    }

    public String getImplementationClass() {
        return implementationClass;
    }

    public boolean isLastUsed() {
        return lastUsed;
    }
    
}
