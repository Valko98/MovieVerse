package org.example.movieverse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    String message() default "Username must be between 3 and 20 characters and contain only letters and numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}