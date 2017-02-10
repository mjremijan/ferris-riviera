package org.ferris.riviera.console.jar;

import java.util.regex.Matcher;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntry extends java.util.jar.JarEntry implements Comparable<JarEntry> {

    @Max(value=99, message = "{JarEntry.major.Max.message}")
    protected Integer major;
    @Max(value=99, message = "{JarEntry.feature.Max.message}")
    protected Integer feature;
    @Max(value=99, message = "{JarEntry.bug.Max.message}")
    protected Integer bug;
    @Max(value=999, message = "{JarEntry.build.Max.message}")
    protected Integer build;
    @Size(min = 0, max = 50, message = "{JarEntry.title.Size.message}")
    protected String title;
    @Size(min = 1, max = 100, message = "{JarEntry.fileName.Size.message}")
    protected String fileName;
    @Size(min = 0, max = 50, message = "{JarEntry.fileDescription.Size.message}")
    protected String fileDescription;



    public JarEntry(Matcher m) {

        // "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1 - description.sql"
        super(m.group(0));

        //"1.0.0"
        String[] releaseVersionTokens
            = m.group(2).split("\\.");
        Integer releaseMajor = Integer.valueOf(releaseVersionTokens[0]);
        Integer releaseFeature = Integer.valueOf(releaseVersionTokens[1]);
        Integer releaseBug = Integer.valueOf(releaseVersionTokens[2]);

        title = m.group(3);
        fileName = m.group(4);
        fileDescription = m.group(6);

        String[] fileVersionTokens
            = m.group(5).split("\\.");

        major = Integer.valueOf(fileVersionTokens[0]);
        feature = Integer.valueOf(fileVersionTokens[1]);
        bug = Integer.valueOf(fileVersionTokens[2]);
        build = Integer.valueOf(fileVersionTokens[3]);

        if (
            major != releaseMajor
            ||
            feature != releaseFeature
            ||
            bug != releaseBug
        ) {
            throw new RuntimeException(
                String.format(
                    "SQL file version does not match directory version \"%s\""
                    , m.group(0)
                )
            );
        }
    }

    public String getVersion() {
        return String.format("%d.%d.%d.%d",
            major,
            feature,
            bug,
            build
        );
    }

    @Override
    public int compareTo(JarEntry other) {

        int retval = 9999;

        if (this.major.compareTo(other.major) < 0) {
            retval = -1;
        } else if (this.major.compareTo(other.major) > 0) {
            retval = 1;
        } else {
            if (this.feature.compareTo(other.feature) < 0) {
                retval = -1;
            } else if (this.feature.compareTo(other.feature) > 0) {
                retval = 1;
            } else {
                if (this.bug.compareTo(other.bug) < 0) {
                    retval = -1;
                } else if (this.bug.compareTo(other.bug) > 0) {
                    retval = 1;
                } else {
                    if (this.build.compareTo(other.build) < 0) {
                        retval = -1;
                    } else if (this.build.compareTo(other.build) > 0) {
                        retval = 1;
                    } else {
                        retval = 0;
                    }
                }
            }
        }

        return retval;
    }
}
