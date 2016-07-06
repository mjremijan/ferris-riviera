package org.ferris.riviera.console.sc;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
/*
              1.0.0
                |
              1.0.1
                |
(public 1.0)  1.0.2-----
                |       \
              2.0.0    1.1.0
                |        |
              2.0.1    1.1.1 (public 1.1)
                |
(public 2.0)  2.0.2-----
                |       \
              3.0.0    2.1.0
                         |
                       2.1.1 (public 2.1)
                         |
                       2.2.0
                         |
                       2.2.1
*/
public class SchemaChange {
    private String major;
    private String minor;
    private String branch;
    private String build;
    private String scriptName;
    private Date appliedOn;

    public SchemaChange(String major, String minor, String branch, String build, String scriptName, Date appliedOn) {
        this.major = major;
        this.minor = minor;
        this.branch = branch;
        this.build = build;
        this.scriptName = scriptName;
        this.appliedOn = appliedOn;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.major);
        hash = 89 * hash + Objects.hashCode(this.minor);
        hash = 89 * hash + Objects.hashCode(this.branch);
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
        final SchemaChange other = (SchemaChange) obj;
        if (!Objects.equals(this.major, other.major)) {
            return false;
        }
        if (!Objects.equals(this.minor, other.minor)) {
            return false;
        }
        if (!Objects.equals(this.branch, other.branch)) {
            return false;
        }
        if (!Objects.equals(this.build, other.build)) {
            return false;
        }
        return true;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public String getBranch() {
        return branch;
    }

    public String getBuild() {
        return build;
    }

    public String getScriptName() {
        return scriptName;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }
}
