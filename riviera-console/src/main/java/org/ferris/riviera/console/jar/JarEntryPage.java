package org.ferris.riviera.console.jar;

import java.util.Map;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.io.Console;
import static org.ferris.riviera.console.jar.JarEntryFinderEvent.VIEW_CONSTRAINT_VIOLATIONS;
import org.ferris.riviera.console.messages.Key;
import org.jboss.weld.experimental.Priority;

@Singleton
public class JarEntryPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    public void viewJarEntryErrors(
          @Observes @Priority(VIEW_CONSTRAINT_VIOLATIONS) JarEntryFinderEvent event
        , @Key("JarEntry.ConstraintViolations.Heading") String heading
        , @Key("JarEntry.ConstraintViolations.Message.singular") String singular
        , @Key("JarEntry.ConstraintViolations.Message.pluralFormat") String pluralFormat
        , @Key("JarEntry.ConstraintViolations.fileFormat") String fileFormat
    ) {
        log.info("ENTER");

        // If no constraint violations, do nothing.
        if (event.getJarEntryConstraintViolations().isEmpty()) {
            return;
        }

        // Output the heading
        console.h1(heading);


        // Get the constraint violations for easy access
        Map<JarEntry, Set<ConstraintViolation<JarEntry>>> constraintViolations
            = event.getJarEntryConstraintViolations();

        // If there is only 1 violation, use singular message
        if (constraintViolations.size() == 1) {
            console.p(singular);
            console.br();
        }
        // Otherwise use the plural message
        else {
            console.p(pluralFormat, String.valueOf(constraintViolations.size()));
            console.br();
        }

        // Loop over all the JarEntries
        constraintViolations.entrySet().stream().forEach(es -> {
            // Display JarEntry
            console.p(fileFormat, es.getKey().getName());

            // Loop over constraint violations
            es.getValue().forEach(cv -> console.li(cv.getMessage()));

            // Display newline
            console.br();
        });
    }
}
