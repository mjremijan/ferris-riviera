package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_NEW_SCRIPTS_TO_APPLY;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPTS_FROM_DATABASE;
import org.jboss.weld.experimental.Priority;

@Singleton
public class ScriptPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    protected ScriptFormat scriptFormat;

    public void viewFromDatabase(
          @Observes @Priority(SHOW_SCRIPTS_FROM_DATABASE) ScriptRetrievalEvent event
        , @Key("ScriptPage.db.Heading") String heading
        , @Key("ScriptPage.db.Created") String created
        , @Key("ScriptPage.db.Found") String found
        , @Key("ScriptPage.db.HistorySizeFormat") String historySizeFormat
        , @Key("ScriptPage.db.LastAppliedVersionFormat") String lastAppliedVersionFormat
        , @Key("ScriptPage.db.LastAppliedVersionNone") String lastAppliedVersionNone
        , @Key("ScriptPage.db.LatestVersionFormat") String latestVersionFormat
        , @Key("ScriptPage.db.LatestVersionNone") String latestVersionNone
    ) {
        console.h1(heading);

        if (event.isTableThere()) {
            console.p(found);
        } else {
            console.p(created);
        }

        Scripts fromDatabase
            = event.getScriptsFromDatabase();

        fromDatabase
            .list()
            .forEach(s -> console.p(scriptFormat.format(s)));
        console.br();

        console.p(
            historySizeFormat
          , String.valueOf(fromDatabase.size())
        );
        console.br();

        Script lastApplied = fromDatabase.getLastAppliedVersion();
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

        Script latest = fromDatabase.getLatestVersion();
        if (latest == null) {
            console.p(latestVersionNone);
        } else {
            console.p(latestVersionFormat, latest.toVersionString());
        }
    }


    /*



    */
    public void viewFromJar(
          @Observes @Priority(SHOW_NEW_SCRIPTS_TO_APPLY) ScriptRetrievalEvent event
        , @Key("ScriptPage.jar.Heading") String heading
        , @Key("ScriptPage.jar.ScriptFileFormat") String scriptFileFormat
        , @Key("ScriptPage.jar.ScriptCountFormat") String scriptCountFormat
        , @Key("ScriptPage.jar.FutureVersionFormat") String futureVersionFormat
    ) {
        // Available Updates
        // -----------------
        console.h1(heading);

        // Found script file 'email-ddl.zip'
        console.p(scriptFileFormat, event.getScriptJarFileName());
        console.br();

        // 1.0.0.1    1.0.0.1[ - Optional].sql
        // 1.0.0.2    1.0.0.2.sql
        // 1.0.0.3    1.0.0.3.sql
        // 1.1.0.0    1.1.0.0[ - New features].sql
        event.getScriptsFromJar()
            .list()
            .forEach(s -> console.p(scriptFormat.format(s)));
        console.br();

        // Script count: 4
        console.p(scriptCountFormat, String.valueOf(event.getScriptsFromJar().size()));
        console.br();

        // Future version: 1.1.0.0
        console.p(futureVersionFormat, event.getScriptsFromJar().getLatestVersion().toVersionString());
    }
}
