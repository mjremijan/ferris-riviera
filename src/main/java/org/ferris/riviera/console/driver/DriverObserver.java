package org.ferris.riviera.console.driver;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.conf.ConfDirectory;
import org.ferris.riviera.console.main.MainEvent;
import static org.ferris.riviera.console.main.MainEvent.DRIVER;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DriverObserver {

    @Inject
    protected Logger log;

    @Inject
    protected ConfDirectory confDirectory;

    public void observes(
        @Observes @Priority(DRIVER) MainEvent event
    ) {
        try {
            Method method
                = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});

            method.setAccessible(true);

            method.invoke(
                (URLClassLoader) ClassLoader.getSystemClassLoader(), new Object[]{confDirectory.getDriverJar().toURI().toURL()}
            );

            log.info(String.format("Added JAR %s", confDirectory.getDriverJar().getAbsolutePath()));
        } catch (Throwable t) {
            throw new RuntimeException(
                String.format(
                      "Failure adding driver JAR file \"%s\" to system classloader"
                    , confDirectory.getDriverJar().getAbsolutePath()
                ), t
            );
        }
    }
}
