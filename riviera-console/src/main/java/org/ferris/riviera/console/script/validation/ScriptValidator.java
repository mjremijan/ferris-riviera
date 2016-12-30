package org.ferris.riviera.console.script.validation;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.ferris.riviera.console.script.Script;
import org.ferris.riviera.console.script.ScriptProcessingEvent;
import static org.ferris.riviera.console.script.ScriptProcessingEvent.VALIDATE_NEW_SCRIPTS_TO_APPLY;
import org.ferris.riviera.console.script.Scripts;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptValidator {

    protected Validator validator;

    public ScriptValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validate(
        @Observes @Priority(VALIDATE_NEW_SCRIPTS_TO_APPLY) ScriptProcessingEvent event
    ) {
        Scripts toApply
            = event.getScriptsToApply();

        Set<ConstraintViolation<Script>> problems
            = new HashSet<>();

        if (toApply != null && !toApply.isEmpty()) {
            toApply.forEach(s -> problems.addAll(validator.validate(s)));
        }

        event.setProblems(problems);
    }
}
