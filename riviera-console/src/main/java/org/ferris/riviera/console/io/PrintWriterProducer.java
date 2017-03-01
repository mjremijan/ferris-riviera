package org.ferris.riviera.console.io;

import java.io.PrintWriter;
import javax.enterprise.inject.Produces;

/**
 * Produces a {@link PrintWriter} used by {@link Console}
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PrintWriterProducer {

    protected PrintWriter writer;
    /**
     * Produces a {@link PrintWriter} wrapping {@code System.out}
     *
     * @return A {@link PrintWriter}, never null.
     */
    @Produces
    public PrintWriter producePrintWriter() {
        if (writer == null) {
            writer = new PrintWriter(System.out);
        }
        return writer;
    }
}
