package org.ferris.riviera.console.script;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ferris.riviera.console.script.constraints.ReleaseVersion;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ReleaseVersion
public class Script implements Comparable<Script> {

    @NotNull
    @Size(min = 1, max = 8)
    final private String releaseVersion;

    @Size(min = 0, max = 50)
    final private String releaseTitle;

    @NotNull
    final private Integer major;

    @NotNull
    final private Integer feature;

    @NotNull
    final private Integer bug;

    @NotNull
    final private Integer build;

    @NotNull
    @Size(min = 1, max = 100)
    final private String fileName;

    @Size(min = 1, max = 50)
    final private String fileDescription;

    @NotNull
    final private Date appliedOn;

    public Script(
        String releaseVersion,
        String releaseTitle,
        Integer major,
        Integer feature,
        Integer bug,
        Integer build,
        String fileName,
        String fileDescription,
        Date appliedOn
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
    }

    public String toVersionString() {
        return String.format("%d.%d.%d.%d",
            major,
            feature,
            bug,
            build
        );
    }

    @Override
    public String toString() {
        return this.fileName;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(major)
            .append(feature)
            .append(bug)
            .append(build)
            .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Script rhs = (Script) obj;
        return new EqualsBuilder()
            .append(major, rhs.major)
            .append(feature, rhs.feature)
            .append(bug, rhs.bug)
            .append(build, rhs.build)
            .isEquals();
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
