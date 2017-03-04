package org.ferris.riviera.console.history;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Map<Date, List<History>> map =
            stream()
                .collect(Collectors.groupingBy(h -> h.getAppliedOn()));

            map.keySet().stream().max((a,b)->a.compareTo(b));

            return Optional.of(stream()
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
