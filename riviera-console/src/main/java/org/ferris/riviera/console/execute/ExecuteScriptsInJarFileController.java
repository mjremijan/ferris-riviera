package org.ferris.riviera.console.execute;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.execute.ExecuteEvent.EXECUTE_SCRIPTS_IN_JAR_FILE;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecuteScriptsInJarFileController {

    @Inject
    protected Logger log;

    @Inject
    protected ExecuteScriptsInJarFilePage page;

    @Inject
    protected ExecuteScriptInJarFileExecutor executor;


    public void observeExecuteScriptsInJarFile(
        @Observes @Priority(EXECUTE_SCRIPTS_IN_JAR_FILE) ExecuteEvent event
    ) {
        log.info("ENTER");

        try {
            event.getJarEntries().stream().forEach(je -> {
                page.showFileThatsBeingRead(je.getName());

                je.setAppliedOn(System.currentTimeMillis());
                event.getJarFile().getJarEntryStatements(je).stream().forEach(sql -> {
                    page.showSQLStatementThatsBeingExecuted(sql);
                    executor.execute(sql);
                });
            });
        } catch (RuntimeException e) {
            event.setFailed(e);
        }
    }
}
