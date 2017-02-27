package org.ferris.riviera.console.jar;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarFile extends java.util.jar.JarFile {

    public JarFile(File jarFile) throws IOException {
        super(jarFile, false);
    }

    public String getFileName() {
        return new File(getName()).getName();
    }

    public long getScriptCount() {
        return this.stream()
            .filter(j -> j.isDirectory() == false)
            .map(j -> new JarEntry(j.getName()))
            .filter(JarEntry::matches)
            .count();
    }

    public List<JarEntry> getComplement(List<String> removeTheseVersions) {
        List<JarEntry> entries = this.stream()
            .filter(j -> !j.isDirectory())
            .map(j -> new JarEntry(j.getName()))
            .filter(JarEntry::matches)
            .filter(j -> !removeTheseVersions.contains(j.getVersion()))
            .collect(Collectors.toList());
        return entries;
    }
}
