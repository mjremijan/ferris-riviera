package org.ferris.riviera.console.script;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Script implements Comparable<Script> {

    private String releaseVersion;
    private String releaseTitle;
    private int major;
    private int feature;
    private int bug;
    private int build;
    private String fileName;
    private String fileDescription;
    private Date appliedOn;

    public Script(
          String releaseVersion
        , String releaseTitle
        , int major
        , int feature
        , int bug
        , int build
        , String fileName
        , String fileDescription
        , Date appliedOn
    ) {
        this.releaseVersion = releaseVersion;
        this.releaseTitle = releaseTitle;
        this.major = major;
        this.feature = feature;
        this.bug = bug;
        this.build = build;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.appliedOn = appliedOn;
        this.appliedOn = appliedOn;
    }

    public String toVersionString() {
        return
        String.format("%d.%d.%d.%d"
            , major
            , feature
            , bug
            , build
        );
    }

    @Override
    public String toString() {
        return this.fileName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.major);
        hash = 89 * hash + Objects.hashCode(this.feature);
        hash = 89 * hash + Objects.hashCode(this.bug);
        hash = 89 * hash + Objects.hashCode(this.build);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Script other
            = (Script) obj;

        return (this.major == other.major
            && this.feature == other.feature
            && this.bug == other.bug
            && this.build == other.build);
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public int getMajor() {
        return major;
    }

    public int getFeature() {
        return feature;
    }

    public int getBug() {
        return bug;
    }

    public int getBuild() {
        return build;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    @Override
    public int compareTo(Script other) {

        int retval = 9999;

        if (this.major < other.major) {
            retval = -1;
        }
        else
        if (this.major > other.major) {
            retval = 1;
        }
        else {
            if (this.feature < other.feature) {
                retval = -1;
            }
            else
            if (this.feature > other.feature) {
                retval = 1;
            }
            else {
                if (this.bug < other.bug) {
                    retval = -1;
                }
                else
                if (this.bug > other.bug) {
                    retval = 1;
                }
                else {
                    if (this.build < other.build) {
                        retval = -1;
                    }
                    else
                    if (this.build > other.build) {
                        retval = 1;
                    }
                    else {
                        retval = 0;
                    }
                }
            }
        }

        return retval;
    }
}
