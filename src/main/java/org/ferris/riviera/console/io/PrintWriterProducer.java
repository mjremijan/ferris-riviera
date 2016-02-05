package org.ferris.riviera.console.io;

import java.io.PrintWriter;
import javax.enterprise.inject.Produces;

/**
 * Produces a {@link PrintWriter} used by {@link Console}
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PrintWriterProducer {

    /**
     * Produces a {@link PrintWriter} wrapping {@code System.out}
     * 
     * @return A {@link PrintWriter}, never null.
     */
    @Produces
    public PrintWriter producePrintWriter() {
        return new PrintWriter(System.out);
    }
}
