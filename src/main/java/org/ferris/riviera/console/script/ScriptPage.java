package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPT_HISTORY;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ScriptPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("ScriptPage.Heading")
    protected String heading;

    @Inject
    @Key("ScriptPage.Created")
    protected String created;

    @Inject
    @Key("ScriptPage.Found")
    protected String found;

    @Inject
    @Key("ScriptPage.HistorySizeFormat")
    protected String historySizeFormat;

    @Inject
    @Key("ScriptPage.CurrentVersionFormat")
    protected String currentVersionFormat;

    @Inject
    @Key("ScriptPage.CurrentVersionNone")
    protected String currentVersionNone;

    @Inject
    protected ScriptFormat scriptFormat;

    public void view(
            @Observes @Priority(SHOW_SCRIPT_HISTORY) ScriptRetrievalEvent event
    ) {
        console.h1(heading);

        if (event.isTableThere()) {
            console.p(found);
        } else {
            console.p(created);
        }

        ScriptHistory history
            = event.getScriptHistoryFromDatabase();

        history
            .list()
            .forEach(s -> console.p(scriptFormat.format(s)));
        console.br();

        console.p(
            historySizeFormat
          , String.valueOf(history.size())
        );
        console.br();


        Script current = history.current();
        if (current == null) {
            console.p(currentVersionNone);
        } else {
            console.p(
                currentVersionFormat
                , String.format("%d.%d.%d.%d"
                    , current.getMajor()
                    , current.getFeature()
                    , current.getBug()
                    , current.getBuild()
                )
            );
        }
    }
}
