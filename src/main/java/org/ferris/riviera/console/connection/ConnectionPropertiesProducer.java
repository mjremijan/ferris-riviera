package org.ferris.riviera.console.connection;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.conf.ConfDirectory;
import org.ferris.riviera.console.lang.StringTool;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionPropertiesProducer {

    @Inject
    protected Logger log;

    @Inject
    protected ConfDirectory confDirectory;

    @Inject
    protected StringTool strt;

    @Produces
    protected ConnectionProperties produceConnectionProperties(
    ) {
        File propertiesFile
            = new File(confDirectory, "connection.properties");

        if (!propertiesFile.exists()) {
            throw new RuntimeException(
                String.format(
                    "File does not exist \"%s\"", propertiesFile.getAbsolutePath())
            );
        }

        Properties props = new Properties();
        try (FileReader reader = new FileReader(propertiesFile)) {
            props.load(reader);
        } catch (Exception e) {
            throw new RuntimeException(
                String.format("Cannot read file \"%s\"", propertiesFile.getAbsoluteFile())
            );
        }

        Arrays.asList("url,username,password".split(","))
            .forEach(k -> {
                String v = strt.trimToNull(props.getProperty(k));
                if (v == null) {
                    throw new RuntimeException(
                        String.format("No value for connection property \"%s\"", k)
                    );
                } else {
                    props.setProperty(k, v);
                    log.info(String.format("Connection property %s=\"%s\"",k,v));
                }
            });

        return new ConnectionProperties(
              props.getProperty("url")
            , props.getProperty("username")
            , props.getProperty("password")
        );
    }
}
