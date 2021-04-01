package com.lansrod.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Constraint(validatedBy = UniqueSocialSecurityNumberValidator.class)
@Target({ FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSocialSecurityNumber {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
