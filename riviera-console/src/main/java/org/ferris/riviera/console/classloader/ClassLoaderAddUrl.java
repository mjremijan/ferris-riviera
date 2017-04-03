package org.ferris.riviera.console.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.driver.DriverFile;
import org.ferris.riviera.console.main.MainEvent;
import static org.ferris.riviera.console.main.MainEvent.ADD_URL;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ClassLoaderAddUrl {

    @Inject
    protected Logger log;

    public void observes(
            @Observes @Priority(ADD_URL) MainEvent event, DriverFile jar
    ) {
        try {
            Method method
                    = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});

            method.setAccessible(true);

            method.invoke(
                    (URLClassLoader) ClassLoader.getSystemClassLoader(), new Object[]{jar.toURI().toURL()}
            );

            log.info(String.format("Added JAR %s", jar.getAbsolutePath()));
        } catch (Throwable t) {
            throw new RuntimeException(
                    String.format(
                            "Failure adding driver JAR file \"%s\" to system classloader", jar.getAbsolutePath()
                    ), t
            );
        }
    }
}
