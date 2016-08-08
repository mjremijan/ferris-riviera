package org.ferris.riviera.console.script;

import java.util.jar.JarEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Dependent
public class ScriptBuilder {

    private JarEntry jarEntry;

    private Pattern p;

    public ScriptBuilder() {
        String dirRegex
            = "((\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?)";
        String fileRegex
            = "((\\d+.{1}\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?\\.sql)";
        p = Pattern.compile(dirRegex + "/" + fileRegex);

    }

    public ScriptBuilder setJarEntry(JarEntry jarEntry) {
        this.jarEntry = jarEntry;
        return this;
    }

    /**
     * Attempt to build a {@link Script} object with whatever has been
     * given to the builder to build the instance of the object.
     *
     * @return
     *  <p>
     *      Return a {@link Script} object if the builder determines it
     *      has what it needs to build the object and it is able to build it.
     *  </p>
     *  <p>
     *      Return {@code null} if the builder determines it cannot build
     *      a {@link Script} with what it's been given.
     *  </p>
     *
     * @throws
     *  <p>
     *      Throw an {@link IllegalArgumentException} if the builder determines
     *      it has what it needs to build the object but is unable to do so
     *      because of some problem.
     *  </p>
     */
    public Script build() {
        if (jarEntry.isDirectory()) {
            return null;
        }

        Matcher m
            = p.matcher(jarEntry.getName());

        if (!m.matches()) {
            return null;
        }

        //String directoryName = m.group(1);
        String directoryVersion = m.group(2);
        //String directoryDescription = m.group(4);

        String fileName = m.group(5);
        String fileVersion = m.group(6);
        //String fileDescription = m.group(8);

        if (!fileVersion.startsWith(directoryVersion)) {
            throw new IllegalArgumentException(
                 String.format(
                    "The JarEntry \"%s\" is invalid because its file name \"%s\" starts with the version \"%s\" but the directory is \"%s\""
                    , m.group(0)
                    , fileName
                    , fileVersion
                    , directoryVersion
                 )
            );
        }

        String[] fileVersionTokens
            = fileVersion.split("\\.");

        Script s = new Script(
              Integer.parseInt(fileVersionTokens[0]) //int major
            , Integer.parseInt(fileVersionTokens[1]) //int feature
            , Integer.parseInt(fileVersionTokens[2]) //int bug
            , Integer.parseInt(fileVersionTokens[3]) //int build
            , fileName
            , null
        );

        return s;
    }
}
