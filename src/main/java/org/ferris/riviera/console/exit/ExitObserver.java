package org.ferris.riviera.console.exit;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.exit.qualifier.Abnormal;
import org.ferris.riviera.console.exit.qualifier.Normal;
import org.ferris.riviera.console.lang.SystemTool;

public class ExitObserver {

    @Inject
    protected Logger log;

    @Inject
    protected SystemTool systemTool;

    @Inject
    protected ExitPage exitPage;

    public void observesAbnormal(@Observes @Abnormal ExitEvent exitEvent) {
        log.info("View page");
        exitPage.view();
        log.info("Abnormal JVM exit");
        systemTool.exitAbnormal();
    }
    
    public void observesNormal(@Observes @Normal ExitEvent exitEvent) {
        log.info("View page");
        exitPage.view();
        log.info("Normal JVM exit");
        systemTool.exitNormal();
    }

}
