package com.company.annotation;

import com.company.annotation.validator.PassportValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PassportValidation.class)
public @interface  Passport {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
