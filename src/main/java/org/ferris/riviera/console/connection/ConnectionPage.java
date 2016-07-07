package org.ferris.riviera.console.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.inject.Inject;
import org.apache.log4j.Logger;
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
    @Key("JdbcPage.Heading")
    protected String heading;

    @Inject
    @Key("JdbcPage.UrlFormat")
    protected String urlFormat;

    @Inject
    @Key("JdbcPage.CredentialsFormat")
    protected String credentialsFormat;

    @Inject
    protected ConnectionHandler handler;


    /**
     * Show the user the database connection information
     */
    public void view() {
        console.h1(heading);
        console.p(urlFormat, handler.getUrl());
        console.p(credentialsFormat, handler.getUsername(), handler.getPassword());

        try {
            Connection conn
                = handler.getConnection();

            Statement stmt
                = conn.createStatement();

            stmt.execute("set schema sys");

            ResultSet rs
                = stmt.executeQuery("select username from sysusers");

            console.h1("SYSUERS");
            while (rs.next()) {
                console.p(rs.getString(1));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
