package org.ferris.riviera.console.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class BufferedReaderProducer {
    protected BufferedReader reader;

    @Produces
    protected BufferedReader produceBufferedReader() {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return reader;
    }
}
