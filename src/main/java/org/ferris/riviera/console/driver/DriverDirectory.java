package org.ferris.riviera.console.driver;

import java.io.File;
import static java.lang.String.format;
import javax.inject.Inject;
import org.ferris.riviera.console.application.ApplicationDirectory;

public class DriverDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public DriverDirectory(ApplicationDirectory ad) {
        super(ad, format("%s", "driver"));
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
    
    public File getDriverProperties() {
        File [] jars 
            = super.listFiles(
                f -> f.isFile() && f.getName().toLowerCase().equals("driver.properties")
            );
        
        if (jars == null || jars.length == 0) {
            throw new RuntimeException(
                String.format("No \"driver.properties\" file in directory \"%s\"", getAbsolutePath())
            );
        }        
        
        return jars[0];   
    }
}
