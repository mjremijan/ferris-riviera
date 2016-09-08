package org.ferris.riviera.console.lang;

/**
 * A tool for the static methods of the {@link System} object for unit testing
 * purposes.
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class SystemTool {

    public void exitAbnormal() {
        System.exit(1);
    }

    public void exitNormal() {
        System.exit(0);
    }
}
