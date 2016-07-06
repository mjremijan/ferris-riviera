package org.ferris.riviera.console.lang;

import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class StringTool {
    public String trimToNull(String trimMe) {
        String retval = null;
        if (trimMe != null) {
            retval = trimMe.trim();
            if (retval.isEmpty()) {
                retval = null;
            }
        }
        return retval;
    }
}
