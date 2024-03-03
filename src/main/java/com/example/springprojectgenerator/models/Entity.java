package com.example.springprojectgenerator.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class Entity {
    @NotNull
    private String name;
    @NotNull
    private List<Field> fields;
}
