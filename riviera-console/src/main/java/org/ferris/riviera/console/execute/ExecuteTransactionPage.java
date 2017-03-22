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

    void transactionStarted() {
        log.info("ENTER");
        console.br();
        console.p(started);
    }
}
