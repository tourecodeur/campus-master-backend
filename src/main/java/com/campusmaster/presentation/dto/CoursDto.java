package com.campusmaster.presentation.dto;

import lombok.Data;

@Data
public class CoursDto {

    private Long id;
    private String titre;
    private String description;
    private String matiere;
    private String module;
    private String semestre;
    private String enseignant;
}
