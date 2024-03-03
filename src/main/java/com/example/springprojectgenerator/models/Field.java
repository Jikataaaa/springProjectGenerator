package com.example.springprojectgenerator.models;

import com.example.springprojectgenerator.models.validations.annotations.ValidAccessModifiers;
import com.example.springprojectgenerator.models.validations.annotations.ValidDataTypes;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Field {
    @ValidAccessModifiers
    private String modifier;
    @NotNull
    @ValidDataTypes
    private String dataType;
    @NotNull
    private String name;
}
