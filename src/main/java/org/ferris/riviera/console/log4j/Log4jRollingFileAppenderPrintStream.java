package org.ferris.riviera.console.log4j;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Log4jRollingFileAppenderPrintStream extends PrintStream {

    public Log4jRollingFileAppenderPrintStream(OutputStream os) {
        super(os);
    }
}
