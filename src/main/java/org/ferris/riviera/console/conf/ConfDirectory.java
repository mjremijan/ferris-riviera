package org.ferris.riviera.console.conf;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Inject;
import org.ferris.riviera.console.application.ApplicationDirectory;
import static java.lang.String.format;

public class ConfDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public ConfDirectory(ApplicationDirectory ad) {
        super(ad, format("%s", "conf"));
    }

    public File getDriverJar() {
        File [] jars
            = super.listFiles(
                f -> f.isFile() && f.getName().toLowerCase().endsWith(".jar")
            );

        if (jars == null || jars.length == 0) {
            throw new RuntimeException(
                String.format("No JAR files in directory \"%s\"", getAbsolutePath())
            );
        }
        else
        if (jars.length > 1) {
            throw new RuntimeException(
                String.format("Multiple JAR files in directory \"%s\"", getAbsolutePath())
            );
        }

        return jars[0];
    }

    public Properties getConnectionProperties() {
        File [] jars
            = super.listFiles(
                f -> f.isFile() && f.getName().toLowerCase().equals("connection.properties")
            );

        if (jars == null || jars.length == 0) {
            throw new RuntimeException(
                String.format("No \"connection.properties\" file in directory \"%s\"", getAbsolutePath())
            );
        }

        Properties props = new Properties();
        try {
            props.load(new FileReader(jars[0]));
        } catch (IOException ex) {
            throw new RuntimeException(
                  String.format("Error reading \"connection.properties\" file in directory \"%s\"", getAbsolutePath())
                , ex
            );
        }
        return props;
    }
}
