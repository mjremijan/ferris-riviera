package org.ferris.riviera.console.jar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public JarEntryStatements getJarEntryStatements(JarEntry jarEntry) {
        try {
            return new JarEntryStatements(
                new BufferedReader(
                    new InputStreamReader(
                        super.getInputStream(jarEntry)
                    )
                ).lines()
                 .parallel().collect(Collectors.joining("\n"))
            );
        } catch (IOException e) {
            throw new RuntimeException(
                String.format("Unable to get SQL statments from JAR_FILE=\"%s\" JAR_ENTRY=\"%s\"",
                    super.getName()
                    , jarEntry.getName()
                )
                , e
            );
        }
    }

}
