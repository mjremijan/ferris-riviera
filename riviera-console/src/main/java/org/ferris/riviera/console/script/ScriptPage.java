package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPT_HISTORY_FROM_DATABASE;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPT_HISTORY_FROM_JAR;
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
    @Key("ScriptPage.LastAppliedVersionFormat")
    protected String lastAppliedVersionFormat;

    @Inject
    @Key("ScriptPage.LastAppliedVersionNone")
    protected String lastAppliedVersionNone;

    @Inject
    @Key("ScriptPage.LatestVersionFormat")
    protected String latestVersionFormat;

    @Inject
    @Key("ScriptPage.LatestVersionNone")
    protected String latestVersionNone;

    @Inject
    protected ScriptFormat scriptFormat;

    public void viewFromDatabase(
            @Observes @Priority(SHOW_SCRIPT_HISTORY_FROM_DATABASE) ScriptRetrievalEvent event
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

        Script lastApplied = history.getLastAppliedVersion();
        if (lastApplied == null) {
            console.p(lastAppliedVersionNone);
        } else {
            console.p(
                lastAppliedVersionFormat
                , String.format("%d.%d.%d.%d"
                    , lastApplied.getMajor()
                    , lastApplied.getFeature()
                    , lastApplied.getBug()
                    , lastApplied.getBuild()
                )
            );
        }
        console.br();

        Script latest = history.getLatestVersion();
        if (latest == null) {
            console.p(latestVersionNone);
        } else {
            console.p(
                latestVersionFormat
                , String.format("%d.%d.%d.%d"
                    , latest.getMajor()
                    , latest.getFeature()
                    , latest.getBug()
                    , latest.getBuild()
                )
            );
        }
    }

    public void viewFromJar(
            @Observes @Priority(SHOW_SCRIPT_HISTORY_FROM_JAR) ScriptRetrievalEvent event
    ) {
        console.h1("FROM JAR FILE");

        ScriptHistory history
            = event.getScriptHistoryFromJar();

        history
            .list()
            .forEach(s -> console.p(scriptFormat.format(s)));
        console.br();

        console.p(
            historySizeFormat
          , String.valueOf(history.size())
        );
        console.br();

    }
}
