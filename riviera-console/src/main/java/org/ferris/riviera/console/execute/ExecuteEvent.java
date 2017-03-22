package org.ferris.riviera.console.execute;

import java.util.Optional;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExecuteEvent {
    public static final int GET_PERMISSION_FROM_USER = 600;

    public static final int START_TRANSACTION = 700;

    protected Optional<Boolean> approved;

    public ExecuteEvent() {
        approved = Optional.empty();
    }

    public Optional<Boolean> isApproved() {
        return approved;
    }

    public void setApproved(Boolean b) {
        approved = Optional.of(b);
    }
}
