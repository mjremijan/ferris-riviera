package org.ferris.riviera.console.conf;

import java.io.File;
import javax.inject.Inject;
import org.ferris.riviera.console.application.ApplicationDirectory;
import static java.lang.String.format;

public class ConfDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public ConfDirectory(ApplicationDirectory ad) {
        super(ad, format("%s", "conf"));
    }
}
