package org.example.movieverse.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && username.matches("^[a-zA-Z0-9]{3,20}$");
    }
}