package org.ferris.riviera.console.driver;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcDriverHandler {

    public List<JdbcDriver> getJdbcDrivers() {
        List<JdbcDriver> drivers
            = new LinkedList<>();
        
        drivers.add(new JdbcDriver(String.class.getName(), false));
        drivers.add(new JdbcDriver(Double.class.getName(), true));
        drivers.add(new JdbcDriver(Integer.class.getName(), false));
        
        return drivers;
    }
}
