package com.example.springprojectgenerator.models.validations.validators;

import com.example.springprojectgenerator.models.enums.AccessModifiers;
import com.example.springprojectgenerator.models.validations.annotations.ValidAccessModifiers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AccessModifiersValidator implements ConstraintValidator<ValidAccessModifiers, String> {

    @Override
    public void initialize(ValidAccessModifiers constraintAnnotation) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> accessModifiersNames = Arrays.stream(AccessModifiers.values()).map(Enum::name).toList();
        return accessModifiersNames.contains(s);
    }
}
