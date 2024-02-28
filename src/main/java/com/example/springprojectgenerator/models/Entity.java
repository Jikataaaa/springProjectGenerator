package com.example.springprojectgenerator.models;

import lombok.Data;

import java.util.List;
@Data
public class Entity {

    private String name;
    private List<Field> fields;
}
