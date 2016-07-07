package org.ferris.riviera.console.connection;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionObserver {
    @Inject
    protected ConnectionPage page;

    public void view(
        @Observes ConnectionEvent event
    ) {
        page.view();
    }

}
