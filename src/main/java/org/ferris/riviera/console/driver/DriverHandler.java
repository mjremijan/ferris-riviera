package org.ferris.riviera.console.driver;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import static org.ferris.riviera.console.driver.DriverEvent.INIT_JAR;
import static org.ferris.riviera.console.driver.DriverEvent.INIT_PROPERTIES;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DriverHandler {

    @Inject
    protected DriverDirectory driverDirectory;
        
    
    public void addDriverJarFileToSystemClassLoader(
        @Observes @Priority(INIT_JAR) DriverEvent event
    ) {
        try {
            Method method 
                = URLClassLoader.class.getDeclaredMethod("addURL",new Class[]{URL.class});
            
            method.setAccessible(true);
            
            method.invoke(
                  (URLClassLoader)ClassLoader.getSystemClassLoader()
                , new Object[]{ driverDirectory.getDriverJar().toURI().toURL() }
            ); 
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException(
                "Failure adding driver JAR file to system classloader", t
            );
        }    
    }
    
    
    public void getDriverProperties(
        @Observes @Priority(INIT_PROPERTIES) DriverEvent event
    ) {
        File driverProperties
            = driverDirectory.getDriverProperties();
        
        try {
            Method method 
                = URLClassLoader.class.getDeclaredMethod("addURL",new Class[]{URL.class});
            
            method.setAccessible(true);
            
            method.invoke(
                  (URLClassLoader)ClassLoader.getSystemClassLoader()
                , new Object[]{ driverDirectory.getDriverJar().toURI().toURL() }
            ); 
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException(
                "Failure adding driver JAR file to system classloader", t
            );
        }    
    }
    
}
