package com.guru.client.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateId implements ConstraintValidator<ValidId, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean flag = false;
        //More validations can be added here.
        if (s != null) {
            if (s.length() > 4) {
                if (s.substring(0, 4).matches("^[a-zA-Z]*$")) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return flag;
    }
}
