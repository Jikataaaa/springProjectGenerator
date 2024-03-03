package com.example.springprojectgenerator.models.validations.validators;

import com.example.springprojectgenerator.models.enums.DataTypes;
import com.example.springprojectgenerator.models.validations.annotations.ValidDataTypes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class DataTypesValidator implements ConstraintValidator<ValidDataTypes, String> {
    @Override
    public void initialize(ValidDataTypes constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> dataTypesNames = Arrays.stream(DataTypes.values()).map(Enum::name).toList();
        return dataTypesNames.contains(s);
    }
}
