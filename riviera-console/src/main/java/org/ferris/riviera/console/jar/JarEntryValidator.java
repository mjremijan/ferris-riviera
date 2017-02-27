package org.ferris.riviera.console.jar;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import static org.ferris.riviera.console.jar.JarEntryValidationEvent.VALIDATE;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class JarEntryValidator {

    @Inject
    protected Logger log;

    protected Validator validator;

    public JarEntryValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected void filterJarEntries(
        @Observes @Priority(VALIDATE) JarEntryValidationEvent event
    ) {
        log.info("ENTER");
        List<JarEntry> entries
            = event.getJarEntries();

        Map<JarEntry, Set<ConstraintViolation<JarEntry>>>
            problems = entries.stream()
                .collect(Collectors.toMap(j -> j, j -> validator.validate(j)))
                .entrySet().stream()
                .filter(es -> !es.getValue().isEmpty())
                .collect(Collectors.toMap(es -> es.getKey(), es -> es.getValue()))
        ;

        event.setJarEntryConstraintViolations(problems);
    }
}
