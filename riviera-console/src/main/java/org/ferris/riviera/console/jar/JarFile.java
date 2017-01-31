package org.ferris.riviera.console.jar;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarFile extends java.util.jar.JarFile {

    protected Pattern pattern;

    public JarFile(File jarFile) throws IOException {
        super(jarFile, false);

        String dirRegex
            = "^((\\d+\\.\\d+\\.\\d+)(?:\\s*-\\s*(\\S{1}.*))?)";
        String fileRegex
            = "((\\d+\\.\\d+\\.\\d+\\.\\d+)(?:\\s*-\\s*(\\S{1}.*))?.sql)$";
        pattern
            = Pattern.compile(dirRegex + "\\/" + fileRegex);
    }

    public String getFileName() {
        return new File(getName()).getName();
    }

    public long getScriptCount() {
        return this.stream()
            .filter(j -> j.isDirectory() == false)
            .map(j -> pattern.matcher(j.getName()))
            .filter(m -> m.matches())
            .count();
    }
}
