package org.ferris.riviera.console.jar;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public List<JarEntry> getComplement(List<String> removeTheseVersions) {
        List<JarEntry> entries = this.stream()
            .filter(j -> !j.isDirectory())
            .map(j -> pattern.matcher(j.getName()))
            .filter( m -> m.matches())
            .map(m -> new JarEntry(m))
            .filter(j -> !removeTheseVersions.contains(j.toVersionString()))
            .collect(Collectors.toList());
        return entries;
    }
}
