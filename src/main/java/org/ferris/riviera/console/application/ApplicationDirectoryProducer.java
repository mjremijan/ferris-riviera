package org.ferris.riviera.console.application;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.enterprise.inject.Produces;

public class ApplicationDirectoryProducer {

    ApplicationDirectory applicationDirectory;

    @Produces
    public ApplicationDirectory getApplicationDirectory() throws URISyntaxException {
        if (applicationDirectory == null) {
            URL jarURL = ApplicationDirectory.class.getProtectionDomain().getCodeSource().getLocation();
            URI jarURI = jarURL.toURI();
            File jarFile = new File(jarURI);
            File appFile = jarFile.getParentFile().getParentFile();
            applicationDirectory = new ApplicationDirectory(appFile.getAbsolutePath());
        }
        return applicationDirectory;
    }
}
