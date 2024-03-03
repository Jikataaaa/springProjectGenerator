package com.example.springprojectgenerator.models.validations.annotations;

import com.example.springprojectgenerator.models.validations.validators.AccessModifiersValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccessModifiersValidator.class)
public @interface ValidAccessModifiers {
    String message() default "Invalid access modifier.";
}
