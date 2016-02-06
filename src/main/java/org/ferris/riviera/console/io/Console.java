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
     * Print the string as title
     * 
     * @param str The string to print
     */
    public void title(String str) {
        writer.println("************************************************************");
        writer.println("*                                                          *");
        writer.printf("* %-57s*%n", str);
        writer.println("*                                                          *");
        writer.println("************************************************************");       
        writer.flush();
    }
    
    /**
     * Print the string as heading1
     * 
     * @param str The string to print
     */
    public void h1(String str) {
        writer.printf("%n%s%n", str);
        for (int i=0; i<str.length(); i++) {
            writer.print("-");
        }
        writer.println();
        writer.println();
        writer.flush();
    }
    
    /**
     * Print the string as a paragraph
     * 
     * @param str The string to print
     */
    public void p(String str) {
        p(str, null);
    }
    
    
    /**
     * Formats the string with the given args then
     * print the string as a paragraph
     * 
     * @param str The format of the string to print
     * @param args The arguemnts for the format
     */
    public void p(String format, String...args) {
        writer.printf("%s%n", String.format(format, args));
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
