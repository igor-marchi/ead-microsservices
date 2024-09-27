package com.ead.authuser.web.controller.validation.username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, String> {

    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        if (userName == null) return false;
        if (userName.trim().isEmpty()) return false;
        if (userName.contains(" ")) return false;
        return true;
    }
}
