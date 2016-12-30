package org.ferris.riviera.console.script.jar;

import java.io.File;
import static java.lang.String.format;
import javax.inject.Inject;
import org.ferris.riviera.console.application.ApplicationDirectory;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptsDirectory extends File {

    private static final long serialVersionUID = 7491901906073281271L;

    @Inject
    public ScriptsDirectory(ApplicationDirectory ad) {
        super(ad, format("%s", "scripts"));
        if (!exists()) {
            throw new RuntimeException(
                String.format(
                      "The directory does not exist: \"%s\""
                    , getAbsolutePath()
                )
            );
        }
    }
}