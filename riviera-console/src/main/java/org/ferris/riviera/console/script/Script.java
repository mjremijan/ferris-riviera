package org.ferris.riviera.console.script;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Script implements Comparable<Script> {

    @NotNull
    @Size(min=1, max=8)
    private String releaseVersion;

    @Size(min=0, max=50)
    private String releaseTitle;

    @NotNull
    private Integer major;

    @NotNull
    private Integer feature;

    @NotNull
    private Integer bug;

    @NotNull
    private Integer build;

    @NotNull
    @Size(min=1, max=100)
    private String fileName;

    @Size(min=1, max=50)
    private String fileDescription;

    @NotNull
    private Date appliedOn;

    public Script(
          String releaseVersion
        , String releaseTitle
        , Integer major
        , Integer feature
        , Integer bug
        , Integer build
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

    public Integer getMajor() {
        return major;
    }

    public Integer getFeature() {
        return feature;
    }

    public Integer getBug() {
        return bug;
    }

    public Integer getBuild() {
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

        if (this.major.compareTo(other.major) < 0) {
            retval = -1;
        }
        else
        if (this.major.compareTo(other.major) > 0) {
            retval = 1;
        }
        else {
            if (this.feature.compareTo(other.feature) < 0) {
                retval = -1;
            }
            else
            if (this.feature.compareTo(other.feature) > 0) {
                retval = 1;
            }
            else {
                if (this.bug.compareTo(other.bug) < 0) {
                    retval = -1;
                }
                else
                if (this.bug.compareTo(other.bug) > 0) {
                    retval = 1;
                }
                else {
                    if (this.build.compareTo(other.build) < 0) {
                        retval = -1;
                    }
                    else
                    if (this.build.compareTo(other.build) > 0) {
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
