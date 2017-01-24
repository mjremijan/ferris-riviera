package org.ferris.riviera.console.connection;

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
import org.ferris.riviera.console.lang.StringTool;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ConnectionPropertiesProducer {

    private ConnectionProperties connectionProperties;

    @Inject
    protected Logger log;

    @Inject
    protected DriverDirectory driverDirectory;

    @Inject
    protected StringTool strt;

    @Produces
    protected ConnectionProperties produceConnectionProperties() {

        log.info("ENTER");
        
        if (connectionProperties == null) {
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

            Map<String, String> scrubbed
                = new HashMap<>();

            Arrays.asList("url,username,password,schema_sql,validation_sql".split(","))
                .forEach(k -> {
                    String v = strt.trimToNull(props.getProperty(k));
                    scrubbed.put(k, v);
                    log.info(String.format("Connection property %s=\"%s\"", k, v));
                });

            Arrays.asList("table_types,table_cat,table_schem_pattern,table_name_pattern".split(","))
                .forEach(k -> {
                    String v = strt.trimUp(props.getProperty(k));
                    scrubbed.put(k, v);
                    log.info(String.format("Connection property %s=\"%s\"", k, v));
                });

            connectionProperties
                = new ConnectionProperties(
                    scrubbed.get("url"), scrubbed.get("username"), scrubbed.get("password"), scrubbed.get("validation_sql"), scrubbed.get("schema_sql"), (scrubbed.get("table_types") != null ? scrubbed.get("table_types").split(",") : null), scrubbed.get("table_cat"), scrubbed.get("table_schem_pattern"), scrubbed.get("table_name_pattern")
                );
        }
        return connectionProperties;
    }
}
