package org.ferris.riviera.console.driver;

import java.io.File;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class DriverFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    public DriverFile(File parent, String child) {
        super(parent, child);
    }
}
