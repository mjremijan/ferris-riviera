package org.ferris.riviera.console.script;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import static org.ferris.riviera.console.script.ScriptRetrievalEvent.SHOW_SCRIPT_HISTORY;
import org.jboss.weld.experimental.Priority;

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

    public void view(
            @Observes @Priority(SHOW_SCRIPT_HISTORY) ScriptRetrievalEvent event
    ) {
        console.h1(heading);

        if (event.isTableThere()) {
            console.p(found);
        } else {
            console.p(created);
        }
        console.br();

        /*
        01.00.0000 on (Thu, 12 Dec 2007, 11:00AM) --sc.01.00.0001.sql
        05.00.0001 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.00.0001.sql
        05.00.0002 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.00.0002.sql
            ...
        05.00.0012 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.00.0012.sql
        05.01.0000 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.01.0000.sql
        05.01.0001 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.01.0001.sql
            ...
        05.01.0019 on (Fri, 13 Dec 2007, 11:26AM) --sc.05.01.0019.sql
        */

        console.p(
              historySizeFormat
            , String.valueOf(event.getScriptHistoryFromDatabase().size())
        );
        console.br();

        console.p(currentVersionFormat, "A.B.C.D");

    }
}
