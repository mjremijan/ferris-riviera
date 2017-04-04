package org.ferris.riviera.console.execute;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

@Singleton
public class ExecuteTransactionPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject @Key("ExecuteTransactionPage.TransactionStarted")
    protected String started;

    @Inject @Key("ExecuteTransactionPage.TransactionCommitting")
    protected String committing;

    @Inject @Key("ExecuteTransactionPage.TransactionRollingBack")
    protected String rollingBack;

    void showThatTransactionHasStarted() {
        log.info("ENTER");
        console.br();
        console.p(started);
    }

    void showThatTransactionIsCommitting() {
        log.info("ENTER");
        console.br();
        console.p(committing);
    }

    void showThatTransactionIsRollingBack() {
        log.info("ENTER");
        console.br();
        console.p(rollingBack);
    }
}
