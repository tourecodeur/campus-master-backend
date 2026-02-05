package com.campusmaster.presentation.dto.crud;

import lombok.Data;

@Data
public class CoursRequest {
    private String titre;
    private String description;
    private Long semestreId;
    /** Optionnel : rattacher le cours à une matière existante */
    private Long matiereId;
    private Long enseignantId;
}
