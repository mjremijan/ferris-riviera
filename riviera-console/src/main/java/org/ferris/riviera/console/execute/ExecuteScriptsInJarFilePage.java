package org.ferris.riviera.console.execute;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

@Singleton
public class ExecuteScriptsInJarFilePage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("ExecuteScriptsInJarFilePage.JarFileFormat")
    protected String jarFileFormat;

    @Inject
    @Key("ExecuteScriptsInJarFilePage.SqlStatementCountFormat")
    protected String sqlStatementCountFormat;
    private int sqlStatementCount;

    @Inject
    @Key("ExecuteScriptsInJarFilePage.SqlStatementFormat")
    protected String sqlStatementFormat;

    protected void showFileThatsBeingRead(String fileName) {
        log.info("ENTER");
        sqlStatementCount = 0;
        console.br();
        console.p(jarFileFormat, fileName);
        console.br();
    }

    protected void showSQLStatementThatsBeingExecuted(String sqlStatement) {
        log.info("ENTER");
        log.info(String.format("%n%s",sqlStatement));
        console.br();
        console.s(sqlStatementCountFormat, String.valueOf(++sqlStatementCount));
        console.br();
        console.s(sqlStatementFormat, sqlStatement);
        console.br();
        console.br();
    }
}
