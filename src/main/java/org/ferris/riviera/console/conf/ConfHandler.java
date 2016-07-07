package org.ferris.riviera.console.conf;

import javax.inject.Inject;
import org.ferris.riviera.console.driver.DriverFile;
import org.ferris.riviera.console.messages.Key;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConfHandler {
    @Inject
    @Key(value = "url", required = true)
    protected String url;

    @Inject
    @Key(value = "username", required = true)
    protected String username;

    @Inject
    @Key(value = "password", required = true)
    protected String password;

    @Inject
    protected DriverFile driverFile;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DriverFile getDriverFile() {
        return driverFile;
    }

}
