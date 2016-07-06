package org.ferris.riviera.console.jdbc;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import static org.ferris.riviera.console.jdbc.JdbcEvent.ADD_DRIVER_JAR_FILE_TO_SYSTEM_CLASSLOADER;
import static org.ferris.riviera.console.jdbc.JdbcEvent.LOAD_CONNECTION_PROPERTIES;
import static org.ferris.riviera.console.jdbc.JdbcEvent.VIEW;
import org.ferris.riviera.console.lang.StringTool;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcObserver {

    @Inject
    protected JdbcDirectory driverDirectory;

    @Inject
    protected StringTool strTool;

    @Inject
    protected JdbcPage page;

    public void addDriverJarFileToSystemClassLoader(
        @Observes @Priority(ADD_DRIVER_JAR_FILE_TO_SYSTEM_CLASSLOADER) JdbcEvent event
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


    public void loadConnectionProperties(
        @Observes @Priority(LOAD_CONNECTION_PROPERTIES) JdbcEvent event
    ) {
        Properties connectionProperties
            = driverDirectory.getConnectionProperties();

        String url = strTool.trimToNull(connectionProperties.getProperty("url"));
        if (url == null) {
            throw new RuntimeException("No \"url\" property value found");
        }

        String username = strTool.trimToNull(connectionProperties.getProperty("username"));
        if (username == null) {
            throw new RuntimeException("No \"username\" property value found");
        }

        String password = strTool.trimToNull(connectionProperties.getProperty("password"));
        if (password == null) {
            throw new RuntimeException("No \"password\" property value found");
        }

        event.setUrl(url);
        event.setUsername(username);
        event.setPassword(password);
    }


    public void view(
        @Observes @Priority(VIEW) JdbcEvent event
    ) {
        page.view(event);
    }

}
