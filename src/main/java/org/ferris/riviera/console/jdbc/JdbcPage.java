package org.ferris.riviera.console.jdbc;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 * View of the welcome page
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JdbcPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("JdbcPage.Heading")
    protected String heading;

    @Inject
    @Key("JdbcPage.UrlFormat")
    protected String urlFormat;

    @Inject
    @Key("JdbcPage.CredentialsFormat")
    protected String credentialsFormat;



    /**
     * Show the user the database connection information
     */
    public void view(JdbcEvent evnt) {
        console.h1(heading);
        console.p(urlFormat, evnt.getUrl());
        console.p(credentialsFormat, evnt.getUsername(), evnt.getPassword());
    }

}
