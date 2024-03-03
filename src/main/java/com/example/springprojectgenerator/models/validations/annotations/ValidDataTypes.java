package com.example.springprojectgenerator.models.validations.annotations;

import com.example.springprojectgenerator.models.validations.validators.AccessModifiersValidator;
import com.example.springprojectgenerator.models.validations.validators.DataTypesValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataTypesValidator.class)
public @interface ValidDataTypes {
    String message() default "Invalid data type.";
}
