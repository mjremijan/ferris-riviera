package org.ferris.riviera.console.script;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Script {
	
    private int major;
    private int feature;
    private int bug;
    private int build;
    private String name;
    private Date appliedOn;

    public Script(int major, int feature, int bug, int build, String name, Date appliedOn) {
        this.major = major;
        this.feature = feature;
        this.bug = bug;
        this.build = build;
        this.name = name;
        this.appliedOn = appliedOn;
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
        
        return (
            this.major == other.major
            &&
            this.feature == other.feature
            &&
            this.bug == other.bug
            &&
            this.build == other.build
        );
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

    public String getName() {
        return name;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }
}
