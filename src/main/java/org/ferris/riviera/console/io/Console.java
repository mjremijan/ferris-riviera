package org.ferris.riviera.console.io;

import java.io.PrintWriter;
import javax.inject.Inject;

/**
 * A wrapper around {@code System.out} and {@code System.in} for the application
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Console {

    @Inject 
    protected PrintWriter writer;
    
    /**
     * Print the string as heading1
     * 
     * @param str The string to print
     */
    public void h1(String str) {
        writer.printf("[%s]%n%n", str);
        writer.flush();
    }
    
    /**
     * Print the string as a paragraph
     * 
     * @param str The string to print
     */
    public void p(String str) {
        writer.printf("%s%n", str);
        writer.flush();
    }
    
    /**
     * Print the throwable stack trace as a paragraph
     * 
     * @param e The throwable to print
     */
    public void p(Throwable e) {
        writer.println();
        e.printStackTrace(writer);
        writer.flush();
    }
}
