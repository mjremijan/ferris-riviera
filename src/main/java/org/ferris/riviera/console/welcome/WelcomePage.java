package org.ferris.riviera.console.welcome;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.application.Application;
import org.ferris.riviera.console.application.ApplicationHandler;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;

/**
 * View of the welcome page
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class WelcomePage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Key("WelcomePage.Title")
    protected String title;

    @Inject
    @Key("WelcomePage.VendorHeading")
    protected String vendorHeading;
    
    @Inject
    @Key("WelcomePage.VendorNameFormat")
    protected String vendorNameFormat;
    
    @Inject
    @Key("WelcomePage.VendorIdFormat")
    protected String vendorIdFormat;
    
    @Inject
    @Key("WelcomePage.ApplicationProperties")
    protected String applicationProperties;

    @Inject
    @Key("WelcomePage.NameFormat")
    protected String nameFormat;
    
    @Inject
    @Key("WelcomePage.VersionFormat")
    protected String versionFormat;
    
    @Inject
    @Key("WelcomePage.UrlFormat")
    protected String urlFormat;
    
    @Inject
    @Key("WelcomePage.MavenVersion")
    protected String mavenVersion;
    
    @Inject
    @Key("WelcomePage.JdkVersion")
    protected String jdkVersion;
    
    @Inject
    protected ApplicationHandler applicationHandler;
    
    /**
     * Show the user the welcome page
     */
    public void view() {
        Application application
            = applicationHandler.getApplication();
        
        console.title(title);
        
        console.h1(vendorHeading);
        console.p(vendorNameFormat, application.getVendor());
        console.p(vendorIdFormat, application.getVendorId());
        
        console.h1(applicationProperties);
        console.p(nameFormat,application.getTitle());
        console.p(versionFormat,application.getVersion());
        console.p(urlFormat,application.getUrl());
        console.p(mavenVersion,application.getCreatedBy());
        console.p(jdkVersion,application.getBuildJdk());
        

    }

}
