package org.ferris.riviera.console.application;

import java.io.File;
import javax.enterprise.inject.Vetoed;

@Vetoed
public class ApplicationDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    public ApplicationDirectory(String path) {
        super(path);
    }

}
