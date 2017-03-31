package org.ferris.riviera.console.execute;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.driver.DriverDirectory;
import org.ferris.riviera.console.lang.BooleanTool;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ExecutePropertiesProducer {

    private ExecuteProperties executeProperties;

    @Inject
    protected Logger log;

    @Inject
    protected BooleanTool booltool;

    @Produces
    protected ExecuteProperties produceExecuteProperties(DriverDirectory driverDirectory) {

        log.info("ENTER");

        if (executeProperties == null) {
            File propertiesFile
                = new File(driverDirectory, "connection.properties");

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

            Map<String, Boolean> scrubbed
                = new HashMap<>();

            Arrays.asList("execute_sql".split(","))
                .forEach(k -> {
                    Boolean v = booltool.trimToBoolean(props.getProperty(k));
                    scrubbed.put(k, v);
                    log.info(String.format("Execute property %s=\"%s\"", k, v));
                });

            executeProperties
                = new ExecuteProperties(
                    scrubbed.get("execute_sql")
                );
        }
        return executeProperties;
    }
}
