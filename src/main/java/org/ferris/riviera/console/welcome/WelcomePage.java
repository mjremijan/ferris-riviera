package org.ferris.riviera.console.welcome;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.inject.Inject;
import org.apache.log4j.Logger;
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
    @Key("WelcomePage.ApplicationProperties")
    protected String applicationProperties;

    @Inject
    @Key("WelcomePage.VersionFormat")
    protected String versionFormat;

    /**
     * Show the user the welcome page
     */
    public void view() {
        console.title(title);
        console.h1(applicationProperties);
        getAttributes();
        //console.p(versionFormat, attributes.getValue("Implementation-Version"));

    }

    public void getAttributes() {
        
        Enumeration resEnum;
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL)resEnum.nextElement();
                    System.out.printf("URL: %s%nCLASS: %s%n", url, url.getClass().getName());
//                    InputStream is = url.openStream();
//                    if (is != null) {
//                        Manifest manifest = new Manifest(is);
//                        Attributes mainAttribs = manifest.getMainAttributes();
//                        String version = mainAttribs.getValue("Implementation-Version");
//                        if(version != null) {
//                            return version;
//                        }
//                    }
                }
                catch (Exception e) {
                    // Silently ignore wrong manifests on classpath?
                }
            }
            
            System.out.printf("%n%n");
            URL jarURL = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            //    URL: jar:file:/C:/Users/Michael/Documents/NetBeansProjects/ferris-riviera/target/unziped/ferris-riviera-2.0.0.0-SNAPSHOT-windows-x86-64-jre/lib/jboss-annotations-api_1.2_spec-1.0.0.Final.jar!/META-INF/MANIFEST.MF
            // jarURL:     file:/C:/Users/Michael/Documents/NetBeansProjects/ferris-riviera/target/unziped/ferris-riviera-2.0.0.0-SNAPSHOT-windows-x86-64-jre/lib/ferris-riviera-2.0.0.0-SNAPSHOT-windows.jar
            System.out.printf("jarURL: %s%n", jarURL);
            
            String uriStr = String.format("jar:%s!/%s", jarURL, JarFile.MANIFEST_NAME);
            URI uri = new URI(uriStr);            
            System.out.printf("MY URI: %s%n", uri);
            
            InputStream is = uri.toURL().openStream();
            Manifest manifest = new Manifest(is);
            Attributes attributes = manifest.getMainAttributes();
            System.out.printf("Looping over attributes...%n");
            for (Object key : attributes.keySet()) {
                Object val = attributes.get(key);
                System.out.printf("key=%s, val=%s%n", key, val);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
