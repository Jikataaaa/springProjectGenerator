package com.example.springprojectgenerator.models;

import com.example.springprojectgenerator.models.validations.annotations.ValidAccessModifiers;
import lombok.Data;

@Data
public class Field {
    @ValidAccessModifiers
    private String modifier;

    private String type;
    private String name;
}
