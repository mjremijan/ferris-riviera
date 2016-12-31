package org.ferris.riviera.console.script.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.ferris.riviera.console.script.validation.ReleaseVersionValidator;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ReleaseVersionValidator.class})
@Documented
public @interface ReleaseVersion {

    String message() default "{Script.ReleaseVersion.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
