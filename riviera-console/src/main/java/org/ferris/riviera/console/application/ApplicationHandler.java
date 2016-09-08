package org.ferris.riviera.console.application;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ApplicationHandler {

    @Inject
    protected Logger log;

    protected Attributes getAttributes() {
        log.info("ENTER");

        // This is an example of a JarUrlConnection URL
        //    URL: jar:file:/C:/Users/Michael/..../jboss-annotations-api_1.2_spec-1.0.0.Final.jar!/META-INF/MANIFEST.MF
        //
        // This is what the jarURL returns above
        // jarURL:     file:/C:/Users/Michael/..../ferris-riviera-2.0.0.0-SNAPSHOT-windows.jar        
        Attributes attributes;
        try {
            URL jarURL
                    = this.getClass().getProtectionDomain().getCodeSource().getLocation();

            URI manifestUri
                    = new URI(String.format("jar:%s!/%s", jarURL, JarFile.MANIFEST_NAME));

            InputStream is = manifestUri.toURL().openStream();
            Manifest manifest = new Manifest(is);
            attributes = manifest.getMainAttributes();
            is.close();
        } catch (Exception e) {
            attributes = new Attributes();
        }

        return attributes;
    }

    public Application getApplication() {
        log.info("ENTER");

        Attributes attributes
                = getAttributes();

        Application application
                = new Application();

        application.setTitle(attributes.getValue("Implementation-Title"));
        application.setVersion(attributes.getValue("Implementation-Version"));
        application.setUrl(attributes.getValue("Implementation-URL"));
        application.setBuildJdk(attributes.getValue("Build-Jdk"));
        application.setCreatedBy(attributes.getValue("Created-By"));
        application.setCreatedOn(attributes.getValue("Build-Time"));
        application.setVendorId(attributes.getValue("Implementation-Vendor-Id"));
        application.setVendor(attributes.getValue("Implementation-Vendor"));

        return application;
    }

//    private void fromStackOverflow() {
//        Enumeration resEnum;
//        try {
//            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
//            while (resEnum.hasMoreElements()) {
//                try {
//                    URL url = (URL)resEnum.nextElement();
//                    System.out.printf("URL: %s%nCLASS: %s%n", url, url.getClass().getName());
//                    InputStream is = url.openStream();
//                    if (is != null) {
//                        Manifest manifest = new Manifest(is);
//                        Attributes mainAttribs = manifest.getMainAttributes();
//                        String version = mainAttribs.getValue("Implementation-Version");
//                        if(version != null) {
//                            return version;
//                        }
//                    }
//                }
//                catch (Exception e) {
//                    // Silently ignore wrong manifests on classpath?
//                }
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
