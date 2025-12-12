package com.campusmaster.web.dto;

import lombok.Data;

/**
 * DTO simplifié pour la consultation de cours côté étudiant.
 */
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
