package org.ferris.riviera.console.script;

import java.sql.ResultSet;
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

    protected Matcher matcher;
    protected ResultSet rs;


    public ScriptBuilder setMatcher(Matcher matcher) {
        this.matcher = matcher;
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

        if (matcher != null) {
            retval = buildFromMatcher();
        }
        else
        if (rs != null) {
            retval = buildFromRs();
        } else {
            throw new IllegalArgumentException("No way to build a Script object");
        }

        rs = null;
        matcher = null;
        return retval;
    }

    private Script buildFromRs() {
        Script retval = null;
        try {
            retval = new Script(
                  rs.getString("RELEASE_VERSION")
                , rs.getString("RELEASE_TITLE")
                , rs.getInt("MAJOR")
                , rs.getInt("FEATURE")
                , rs.getInt("BUG")
                , rs.getInt("BUILD")
                , rs.getString("FILE_NAME")
                , rs.getString("FILE_DESCRIPTION")
                , rs.getDate("APPLIED_ON")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    private Script buildFromMatcher() {
        Matcher m = matcher;

        String entireString = m.group(0);
        String directoryName = m.group(1);
        String releaseVersion = m.group(2);
        String releaseTitle = m.group(3);
        String fileName = m.group(4);
        String fileVersion = m.group(5);
        String fileDescription = m.group(6);

        String[] fileVersionTokens
            = fileVersion.split("\\.");

        Script s = new Script(
              releaseVersion
            , releaseTitle
            , Integer.parseInt(fileVersionTokens[0]) //int major
            , Integer.parseInt(fileVersionTokens[1]) //int feature
            , Integer.parseInt(fileVersionTokens[2]) //int bug
            , Integer.parseInt(fileVersionTokens[3]) //int build
            , fileName
            , fileDescription
            , null // appliedOn
        );

        return s;
    }
}
