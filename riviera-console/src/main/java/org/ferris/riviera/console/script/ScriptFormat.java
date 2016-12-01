package org.ferris.riviera.console.script;

import java.text.SimpleDateFormat;
import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ScriptFormat {

    public String format(Script s) {

        String version
            = s.toVersionString();

        String timestamp
            = (s.getAppliedOn() == null) ? null : new SimpleDateFormat("E, dd MMM yyyy, hh:mm a").format(s.getAppliedOn());

        String fileName
            = s.getFileName();

        StringBuilder sp = new StringBuilder();
        {
            sp.append(String.format("%-9s    ", version));
            if (timestamp != null) {
                sp.append(String.format("(%s)    ", timestamp));
            }
            sp.append(fileName);
        }
        return sp.toString();
    }
}
