package org.ferris.riviera.console.jar.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.ferris.riviera.console.jar.JarEntry;
import org.ferris.riviera.console.jar.constraints.JarEntryConstraints;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JarEntryConstraintsValidator implements ConstraintValidator<JarEntryConstraints, JarEntry> {

    @Override
    public boolean isValid(JarEntry t, ConstraintValidatorContext cvc) {
        boolean valid =
            t.getFileVersion().startsWith(t.getReleaseVersion());
        return valid;
    }

    @Override
    public void initialize(JarEntryConstraints a) {
    }
}