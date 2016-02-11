package org.ferris.riviera.console.driver;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriver {
    private Class implementationClass;

    public JdbcDriver(Class implementationClass) {
        this.implementationClass = implementationClass;
    }

    public Class getImplementationClass() {
        return implementationClass;
    }
    
}
