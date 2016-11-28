package org.ferris.riviera.console.script;

import java.sql.ResultSet;
import java.util.jar.JarEntry;
import java.util.regex.Matcher;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Dependent
public class ScriptBuilder {

    @Inject
    protected Logger log;

    protected JarEntry jarEntry;
    protected ResultSet rs;


    public ScriptBuilder setJarEntry(JarEntry jarEntry) {
        this.jarEntry = jarEntry;
        return this;
    }

    public ScriptBuilder setResultSet(ResultSet rs) {
        this.rs = rs;
        return this;
    }

    /**
     * Attempt to build a {@link Script} object with whatever has been given to the builder to build
     * the instance of the object.
     *
     * @return Return a {@link Script} object if the builder determines it has what it needs to
     * build the object and it is able to build it.
     * <p>
     * Return {@code null} if the builder determines it cannot build a {@link Script} with what it's
     * been given.
     * </p>
     *
     * @throws IllegalArgumentException Throw an {@link IllegalArgumentException} if the builder
     * determines it has what it needs to build the object but is unable to do so because of some
     * problem.
     */
    public Script build() {
        Script retval = null;

        if (jarEntry != null) {
            retval = buildFromJarEntry();
            jarEntry = null;
        } else {
            if (rs != null) {
                retval = buildFromRs();
                rs = null;
            } else {
                throw new IllegalArgumentException("No way to build a Script object");
            }
        }

        return retval;
    }

    private Script buildFromRs() {
        Script retval = null;
        try {
            retval = new Script(
                  rs.getInt("MAJOR")
                , rs.getInt("FEATURE")
                , rs.getInt("BUG")
                , rs.getInt("BUILD")
                , rs.getString("NAME")
                , rs.getDate("APPLIED_ON")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    private Script buildFromJarEntry() {

        Matcher m = null;

        //String directoryName = m.group(1);
        String directoryVersion = m.group(2);
        //String directoryDescription = m.group(4);

        String fileName = m.group(5);
        String fileVersion = m.group(6);
        //String fileDescription = m.group(8);

        if (!fileVersion.startsWith(directoryVersion)) {
            throw new IllegalArgumentException(
                String.format(
                    "The JarEntry \"%s\" is invalid because its file name \"%s\" starts with the version \"%s\" but the directory is \"%s\"", m.group(0), fileName, fileVersion, directoryVersion
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
            , fileName, null
        );

        return s;
    }
}
