package org.ferris.riviera.console.jar;

import java.io.File;
import static java.lang.String.format;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.ferris.riviera.console.application.ApplicationDirectory;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class JarDirectory extends File {

    private static final long serialVersionUID = 7491901906073281271L;

    @Inject
    public JarDirectory(ApplicationDirectory ad) {
        super(ad, format("%s", "scripts"));
        if (!exists()) {
            throw new RuntimeException(
                String.format(
                      "The directory \"scripts\" does not exist: \"%s\""
                    , getAbsolutePath()
                )
            );
        }
    }
}