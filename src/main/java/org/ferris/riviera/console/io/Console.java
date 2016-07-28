package org.ferris.riviera.console.io;

import java.io.PrintWriter;
import java.util.Collections;
import javax.inject.Inject;

/**
 * A wrapper around {@code System.out} and {@code System.in} for the application
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Console {

    private void indentf(int indent, String format, String... args) {
        writer.printf(
              "%s%s%n"
            , Collections.nCopies(indent, " ").stream().reduce((t, u) -> t + u).get()
            , String.format(format, args)
        );
        writer.flush();
    }

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
        writer.printf("%n%n%s%n", str);
        for (int i = 0; i < str.length(); i++) {
            writer.print("-");
        }
        writer.println();
        writer.println();
        writer.flush();
    }

    public void br() {
        writer.println();
        writer.flush();
    }

    public void li(String str, String... args) {
        indentf(6, str, args);
    }

    /**
     * Formats the string with the given args then print the string as a
     * paragraph
     *
     * @param str The format of the string to print
     * @param args The arguemnts for the format
     */
    public void p(String format, String... args) {
        indentf(3, format, args);
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
