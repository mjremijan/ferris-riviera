package org.ferris.riviera.console.jar.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.ferris.riviera.console.jar.validation.JarEntryConstraintsValidator;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {JarEntryConstraintsValidator.class})
@Documented
public @interface JarEntryConstraints {

    String message() default "{JarEntry.JarEntryConstraints.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
