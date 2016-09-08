package org.ferris.riviera.console.messages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ResourceBundle;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Qualifier uses to specify the key value to lookup a string from a
 * {@link ResourceBundle}
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Key {

    @Nonbinding
    String value() default "";
}
