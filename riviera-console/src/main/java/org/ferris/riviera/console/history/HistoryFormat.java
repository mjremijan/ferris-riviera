package org.ferris.riviera.console.history;

import java.text.SimpleDateFormat;
import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class HistoryFormat {
    public String format(History s) {
        // EXAMPLES
        // 1.0.0.99     (Wed, 12 Dec 2007, 05:05 PM)    1.0.0.99 - First.sql
        // 1.0.0.1      (Wed, 12 Dec 2007, 05:05 PM)    1.0.0.1.sql
        // 1.15.0.10    (Wed, 12 Dec 2007, 05:05 PM)    1.15.0.10-Wow.sql
        StringBuilder sp = new StringBuilder();
        {
            // version
            sp.append(String.format("%-9s    ", s.toVersionString()));

            // timestamp
            sp.append(String.format("(%s)    ", new SimpleDateFormat("E, dd MMM yyyy, hh:mm:ss.SSS a").format(s.getAppliedOn())));

            // file name
            sp.append(s.getFileName());
        }
        return sp.toString();
    }
}
