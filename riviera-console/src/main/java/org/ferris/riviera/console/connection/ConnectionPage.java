package org.ferris.riviera.console.connection;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.connection.ConnectionValidationEvent.VIEW;
import org.ferris.riviera.console.driver.DriverFile;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 * View of the welcome page
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("ConnectionPage.Heading")
    protected String heading;

    @Inject
    @Key("ConnectionPage.UrlFormat")
    protected String urlFormat;

    @Inject
    @Key("ConnectionPage.CredentialsFormat")
    protected String credentialsFormat;

    @Inject
    @Key("ConnectionPage.DriverFormat")
    protected String driverFormat;

    @Inject
    @Key("ConnectionPage.ValidationSqlFormat")
    protected String validationSqlFormat;

    @Inject
    @Key("ConnectionPage.ValidationResultFormat")
    protected String validationResultFormat;

    /**
     * Show the user the welcome page
     * @param event Event for processing
     * @param driverFile JDBC driver file
     * @param connectionProperties connection.properties
     */
    public void view(
        @Observes @Priority(VIEW) ConnectionValidationEvent event
        , DriverFile driverFile
        , ConnectionProperties connectionProperties
    ) {
        console.h1(heading);
        console.p(urlFormat
            , connectionProperties.getUrl());
        console.p(credentialsFormat
            , connectionProperties.getUsername(), connectionProperties.getPassword());
        console.p(driverFormat
            , driverFile.getAbsolutePath());
        console.p(validationSqlFormat
            , event.getValidationSql());
        console.p(validationResultFormat
            , event.getValidationResult());
    }
}
