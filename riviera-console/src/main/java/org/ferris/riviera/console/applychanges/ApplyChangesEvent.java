package org.ferris.riviera.console.applychanges;

import java.util.Optional;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ApplyChangesEvent {
    public static final int GET_APPROVAL = 600;

    public static final int GO = 700;

    protected Optional<Boolean> approved;

    public ApplyChangesEvent() {
        approved = Optional.empty();
    }

    public Optional<Boolean> isApproved() {
        return approved;
    }

    public void setApproved(Boolean b) {
        approved = Optional.of(b);
    }
}
