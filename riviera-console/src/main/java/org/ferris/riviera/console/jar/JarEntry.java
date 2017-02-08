package org.ferris.riviera.console.jar;

import java.util.regex.Matcher;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.ferris.riviera.console.jar.constraints.JarEntryConstraints;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@JarEntryConstraints
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
    protected Integer major;

    @NotNull(message = "{JarEntry.feature.NotNull.message}")
    protected Integer feature;

    @NotNull(message = "{JarEntry.bug.NotNull.message}")
    protected Integer bug;

    @NotNull(message = "{JarEntry.build.NotNull.message}")
    protected Integer build;

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

        major = Integer.valueOf(fileVersionTokens[0]);
        feature = Integer.valueOf(fileVersionTokens[1]);
        bug = Integer.valueOf(fileVersionTokens[2]);
        build = Integer.valueOf(fileVersionTokens[3]);
    }

    public String toVersionString() {
        return String.format("%d.%d.%d.%d",
            major,
            feature,
            bug,
            build
        );
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public String getFileVersion() {
        return fileVersion;
    }
}
