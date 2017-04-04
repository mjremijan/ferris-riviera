package org.ferris.riviera.console.execute;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ExecuteSkip
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class ExecuteSkipInterceptor {

    @Inject
    protected Logger log;

    @Inject
    protected ExecuteProperties executeProperties;

    @AroundInvoke
    public Object executionSkipOrNot(InvocationContext ctx) throws Exception {
        if (executeProperties.getExecuteSql()) {
            log.info("Interceptor proceeds with execution");
            return ctx.proceed();
        } else {
            log.info("Interceptor skips execution");
            return null;
        }
    }
}
