package org.ferris.riviera.console.driver;

import java.io.File;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DriverFileProducer {

    private DriverFile driverFile;

    @Produces
    public DriverFile produceDriverJarFile(DriverDirectory driverDirectory) {
        if (driverFile == null) {
            File[] jars
                = driverDirectory.listFiles(
                    f -> f.isFile() && f.getName().toLowerCase().endsWith(".jar")
                );

            if (jars == null || jars.length == 0) {
                throw new RuntimeException(
                    String.format("No JAR files in directory \"%s\"", driverDirectory.getAbsolutePath())
                );
            } else if (jars.length > 1) {
                throw new RuntimeException(
                    String.format("Multiple JAR files in directory \"%s\"", driverDirectory.getAbsolutePath())
                );
            }

            driverFile
                = new DriverFile(driverDirectory, jars[0].getName());
        }

        return driverFile;
    }
}
