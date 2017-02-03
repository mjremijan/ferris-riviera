package org.ferris.riviera.console.jar;

import java.util.regex.Matcher;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntry extends java.util.jar.JarEntry {

    protected String directoryName;

    @NotNull(message = "{JarEntry.releaseVersion.NotNull.message}")
    @Size(min = 1, max = 8, message = "{JarEntry.releaseVersion.Size.message}")
    protected String releaseVersion;

    @Size(min = 0, max = 50, message = "{JarEntry.releaseTitle.Size.message}")
    protected String releaseTitle;

    @NotNull(message = "{JarEntry.fileName.NotNull.message}")
    @Size(min = 1, max = 100, message = "{JarEntry.fileName.Size.message}")
    protected String fileName;

    protected String fileVersion;

    @Size(min = 0, max = 50, message = "{JarEntry.fileDescription.Size.message}")
    protected String fileDescription;

    @NotNull(message = "{JarEntry.major.NotNull.message}")
    protected int major;

    @NotNull(message = "{JarEntry.feature.NotNull.message}")
    protected int feature;

    @NotNull(message = "{JarEntry.bug.NotNull.message}")
    protected int bug;

    @NotNull(message = "{JarEntry.build.NotNull.message}")
    protected int build;

    public JarEntry(Matcher m) {
        super(m.group(0));

        directoryName = m.group(1);
        releaseVersion = m.group(2);
        releaseTitle = m.group(3);
        fileName = m.group(4);
        fileVersion = m.group(5);
        fileDescription = m.group(6);

        String[] fileVersionTokens
            = fileVersion.split("\\.");

        major = Integer.parseInt(fileVersionTokens[0]);
        feature = Integer.parseInt(fileVersionTokens[1]);
        bug = Integer.parseInt(fileVersionTokens[2]);
        build = Integer.parseInt(fileVersionTokens[3]);
    }

    public String toVersionString() {
        return String.format("%d.%d.%d.%d",
            major,
            feature,
            bug,
            build
        );
    }
}
