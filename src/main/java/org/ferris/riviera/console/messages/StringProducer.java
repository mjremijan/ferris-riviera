package org.ferris.riviera.console.messages;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.ferris.riviera.console.conf.ConfDirectory;
import org.ferris.riviera.console.lang.StringTool;

/**
 * Uses a {@link ResourceBundle} to {@code /ApplicationMessages[__].properties}
 * at the root of the class-path to find localized strings for the application
 *
 * @author @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class StringProducer {

    @Inject
    protected StringTool strTool;

    protected ResourceBundle rb;
    protected Properties pr;

    /**
     * Creates the {@link ResourceBundle}
     */
    @Inject
    public StringProducer(ConfDirectory confDirectory) {
        rb = ResourceBundle.getBundle("ApplicationMessages");
        pr = confDirectory.getConnectionProperties();

    }

    /**
     * Uses the {@code InjectionPoint ip} to get the {@link Key} qualifier
     * which has the value of the resource bundle key to lookup.
     *
     * @param ip Used to get the {@link Key} qualifier on the injection point.
     *
     * @return Resource bundle value or {@code "<missing>"} if the key is not found.
     */
    @Produces
    @Key
    public String produceString(InjectionPoint ip) {
        Key m = ip.getAnnotated().getAnnotation(Key.class);
        String key = m.value();
        boolean req = m.required();

        String val = null;
        try {
            val = rb.getString(key);
        } catch (MissingResourceException e) {
            val = pr.getProperty(key);
            val = strTool.trimToNull(val);
        }


        if (val == null) {
            if (req) {
                throw new RuntimeException(
                    String.format("No \"%s\" property value found", key)
                );
            } else {
                val = "<missing>";
            }
        }

        return val;
    }
}
