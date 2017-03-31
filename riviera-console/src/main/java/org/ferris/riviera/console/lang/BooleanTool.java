package org.ferris.riviera.console.lang;

import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class BooleanTool {

    public Boolean trimToBoolean(String trimMe) {
        Boolean retval = Boolean.FALSE;
        if (trimMe != null) {
            trimMe = trimMe.trim();
            if (!trimMe.isEmpty() && "true".equalsIgnoreCase(trimMe)) {
                retval = Boolean.TRUE;
            }
        }
        return retval;
    }
}
