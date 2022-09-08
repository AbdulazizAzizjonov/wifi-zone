package com.company.annotation.validator;

import com.company.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

public class PhoneValidation implements ConstraintValidator<Phone, String> {
    private static final String
            PHONE_PATTERN = "(?:\\+[0-9]{12})";
    private static final Pattern
            PATTERN = Pattern.compile(PHONE_PATTERN);

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (Optional.ofNullable(phone).isPresent()) {
            return PATTERN.matcher(phone).matches();
        }
        return false;
    }
}
