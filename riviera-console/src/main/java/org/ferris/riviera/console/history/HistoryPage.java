package org.ferris.riviera.console.history;

import static java.lang.String.valueOf;
import java.util.Optional;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.history.HistoryFinderEvent.VIEW;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import javax.annotation.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class HistoryPage {
    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    protected HistoryFormat formatter;

    public void viewOfHistory(
          @Observes @Priority(VIEW) HistoryFinderEvent event
        , @Key("HistoryPage.Heading") String heading
        , @Key("HistoryPage.CountFormat") String countFormat
        , @Key("HistoryPage.LastAppliedVersionFormat") String lastAppliedVersionFormat
        , @Key("HistoryPage.LastAppliedVersionNone") String lastAppliedVersionNone
        , @Key("HistoryPage.LatestVersionFormat") String latestVersionFormat
        , @Key("HistoryPage.LatestVersionNone") String latestVersionNone
    ) {

        // Heading
        // Script History
        console.h1(heading);

        // Loop and print each script history item
        // 1.0.0.99     (Wed, 12 Dec 2007, 05:05 PM)    1.0.0.99 - First.sql
        event.getHistory()
            .forEach(s -> console.p(formatter.format(s)));
        console.br();

        // Script count: %s
        console.p(countFormat
          , valueOf(event.getHistory().size())
        );
        console.br();

        // Last applied version: %s || NONE
        Optional<History> lastApplied = event.getHistory().getLastAppliedVersion();
        if (lastApplied.isPresent()) {
            console.p(lastAppliedVersionFormat, lastApplied.get().toVersionString());
        } else {
            console.p(lastAppliedVersionNone);
        }
        console.br();

        // Latest version: %s || NONE
        Optional<History> latest = event.getHistory().getLatestVersion();
        if (latest.isPresent()) {
            console.p(latestVersionFormat, latest.get().toVersionString());
        } else {
            console.p(latestVersionNone);
        }
    }
}
