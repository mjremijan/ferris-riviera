package org.ferris.riviera.console.table;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TableValidationEvent {
    public static final int FIND = 100;
    public static final int CREATE = 120;
    public static final int VIEW = 130;

    protected Boolean wasFound;
    protected Boolean wasCreated;

    protected void setWasFound(boolean b) {
        this.wasFound = b;
    }

    protected Boolean wasFound() {
        return this.wasFound;
    }

    protected void setWasCreated(boolean b) {
        this.wasCreated = b;
    }

    protected Boolean wasCreated() {
        return this.wasCreated;
    }
}
