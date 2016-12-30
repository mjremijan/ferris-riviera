package org.ferris.riviera.console.script.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.ferris.riviera.console.script.Script;
import org.ferris.riviera.console.script.constraints.ReleaseVersion;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ReleaseVersionValidator implements ConstraintValidator<ReleaseVersion, Script> {

    @Override
    public boolean isValid(Script t, ConstraintValidatorContext cvc) {
        boolean valid =
            t.getFileName().startsWith(t.getReleaseVersion());

        return valid;
    }

    @Override
    public void initialize(ReleaseVersion a) {
    }
}