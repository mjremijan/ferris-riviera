package org.ferris.riviera.console.script;

import java.io.File;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ScriptJar extends File {

    private static final long serialVersionUID = 14370985973453748L;

    public ScriptJar(File parent, String child) {
        super(parent, child);
    }
}
