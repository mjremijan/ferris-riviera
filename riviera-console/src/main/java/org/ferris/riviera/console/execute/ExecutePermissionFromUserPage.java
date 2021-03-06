package org.ferris.riviera.console.execute;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

@Singleton
public class ExecutePermissionFromUserPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject @Key("ExecutePermissionFromUserPage.YesNo")
    protected String yesNo;

    @Inject @Key("ExecutePermissionFromUserPage.View")
    protected String view;

    @Inject @Key("ExecutePermissionFromUserPage.Title")
    protected String title;

    @Inject
    protected ExecuteProperties executeProperties;

    void showPermissionGrantedAndStartingScriptExecution() {
        console.br();
        console.title(title);
    }

    void askPermissionFromUser(ExecuteEvent event) {
        log.info("ENTER");

        console.br();
        while (!event.isApproved().isPresent())
        {
            // Display the prompt
            if (executeProperties.getExecuteSql()) {
                console.prompt(yesNo);
            } else {
                console.prompt(view);
            }

            // Get user input
            String line = console.readLine();

            // trim, etc
            if (line == null) { line = ""; }
            line = line.trim().toLowerCase();

            // yes
            if ("y".equals(line) || "yes".equals(line)) {
                event.setApproved(TRUE);
            }
            // no
            else
            if ("n".equals(line) || "no".equals(line)) {
                event.setApproved(FALSE);
            }
        }
    }
}
