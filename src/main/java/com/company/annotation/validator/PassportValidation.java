package com.company.annotation.validator;

import com.company.annotation.Passport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidation implements ConstraintValidator<Passport, String> {

    private static final String
    PASSPORT_PATTERN="^(?:[A-Z]{2})(?:[0-9]{7})$";
    private static final Pattern
    PATTERN=Pattern.compile(PASSPORT_PATTERN);

    @Override
    public void initialize(Passport constraintAnnotation) {

    }

    @Override
    public boolean isValid(String passport, ConstraintValidatorContext constraintValidatorContext) {
        if (Optional.ofNullable(passport).isPresent()) {
            Matcher matcher = PATTERN.matcher(passport);
            return matcher.matches();
        }
        return false;
    }
}
