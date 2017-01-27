package org.ferris.riviera.console.history;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryList extends ArrayList<History> {

    private static final long serialVersionUID = 116498641946549L;

    public Optional<History> getLastAppliedVersion() {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(stream()
                .filter(s -> s.getAppliedOn() != null)
                .max(Comparator.comparing(History::getAppliedOn)).get());
        }
    }


    public Optional<History> getLatestVersion() {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(get(size() - 1));
        }
    }

}
