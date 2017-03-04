package org.ferris.riviera.console.history;

import java.sql.Timestamp;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class History {
    final private String releaseVersion;
    final private String releaseTitle;
    final private Integer major;
    final private Integer feature;
    final private Integer bug;
    final private Integer build;
    final private String fileName;
    final private String fileDescription;
    final private Timestamp appliedOn;

    public History(
        String releaseVersion,
        String releaseTitle,
        Integer major,
        Integer feature,
        Integer bug,
        Integer build,
        String fileName,
        String fileDescription,
        Timestamp appliedOn
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
        History rhs = (History) obj;
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

    public Timestamp getAppliedOn() {
        return appliedOn;
    }
}
