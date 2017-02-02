package org.ferris.riviera.console.jar;

import java.util.regex.Matcher;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntry extends java.util.jar.JarEntry {

    protected String directoryName;
    protected String releaseVersion;
    protected String releaseTitle;
    protected String fileName;
    protected String fileVersion;
    protected String fileDescription;
    protected int major;
    protected int feature;
    protected int bug;
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
