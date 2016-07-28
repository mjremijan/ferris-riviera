package org.ferris.riviera.console.connection;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    protected ConnectionProperties produceConnectionProperties() {
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

        Map<String, String> scrubbed
                = new HashMap<>();

        Arrays.asList("url,username,password,set_schema".split(","))
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

        return new ConnectionProperties(
                scrubbed.get("url"), scrubbed.get("username"), scrubbed.get("password"), scrubbed.get("set_schema"), (scrubbed.get("table_types") != null ? scrubbed.get("table_types").split(",") : null), scrubbed.get("table_cat"), scrubbed.get("table_schem_pattern"), scrubbed.get("table_name_pattern")
        );
    }
}
