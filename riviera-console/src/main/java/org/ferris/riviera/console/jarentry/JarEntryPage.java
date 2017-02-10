package org.ferris.riviera.console.jarentry;

import java.util.Map;
import java.util.Set;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.exit.ExitEvent;
import org.ferris.riviera.console.exit.qualifier.Abnormal;
import org.ferris.riviera.console.io.Console;
import org.ferris.riviera.console.messages.Key;
import org.jboss.weld.experimental.Priority;

@Singleton
public class JarEntryPage {

    @Inject
    protected Logger log;

    @Inject
    protected Console console;

    @Inject
    @Abnormal
    protected Event<ExitEvent> exitEvent;

    @Key("JarEntryPage.ConstraintViolations.Heading")
    protected String violationHeading;

    @Key("JarEntryPage.ConstraintViolations.Message.singular")
    protected String violationSingularMessage;

    @Key("JarEntryPage.ConstraintViolations.Message.pluralFormat")
    protected String violationPluralMessageFormat;

    @Key("JarEntryPage.ConstraintViolations.FileFormat")
    protected String violationFileFormat;

    @Key("JarEntryPage.Ready.Heading")
    protected String readyHeading;

    @Key("JarEntryPage.Ready.FileFormat")
    protected String readyFileFormat;

    @Key("JarEntryPage.Ready.ScriptCountFormat")
    protected String readyScriptCountFormat;

    @Key("JarEntryPage.Ready.FutureVersionFormat")
    protected String readyFutureVersionFormat;

    @Key("JarEntryPage.Ready.FutureVersionUpToDate")
    protected String readyFutureVersionUpToDate;


    public void viewJarEntries(
          @Observes @Priority(JarEntryValidationEvent.VIEW) JarEntryValidationEvent event
    ) {
        log.info("ENTER");

        if (event.getJarEntryConstraintViolations().isEmpty()) {
            ready(event);
        } else {
            violations(event);
        }
    }

    protected void ready(JarEntryValidationEvent event) {

        // Output the heading
        console.h1(readyHeading);

        // Loop over all the JarEntries
        event.getJarEntries().stream().forEach(j -> {
            // Display JarEntry
            console.p(readyFileFormat, j.getName());
        });

        // Display newline
        console.br();

        // Display script count
        console.p(readyScriptCountFormat, String.valueOf(event.getJarEntries().size()));

        // Display newline
        console.br();

        // Display future version
        console.p(readyFutureVersionFormat, );


    }

    protected void violations(JarEntryValidationEvent event) {

        // Output the heading
        console.h1(violationHeading);

        // Get the constraint violations for easy access
        Map<JarEntry, Set<ConstraintViolation<JarEntry>>> constraintViolations
            = event.getJarEntryConstraintViolations();

        // If there is only 1 violation, use singular message
        if (constraintViolations.size() == 1) {
            console.p(violationSingularMessage);
            console.br();
        }
        // Otherwise use the plural message
        else {
            console.p(violationPluralMessageFormat, String.valueOf(constraintViolations.size()));
            console.br();
        }

        // Loop over all the JarEntries
        constraintViolations.entrySet().stream().forEach(es -> {
            // Display JarEntry
            console.p(violationFileFormat, es.getKey().getName());
            console.br();

            // Loop over and display constraint violations
            es.getValue().forEach(cv -> console.li(cv.getMessage()));

            // Display newline
            console.br();
        });

        // Abnormal exit
        log.info("Firing abnormal ExitEvent");
        exitEvent.fire(new ExitEvent());
    }
}
