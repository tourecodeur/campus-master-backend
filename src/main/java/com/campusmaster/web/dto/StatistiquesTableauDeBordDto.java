package com.campusmaster.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO pour le tableau de bord analytique.
 */
@Data
@Builder
public class StatistiquesTableauDeBordDto {

    private long nombreEtudiantsActifs;
    private double tauxRemiseDevoirs;
    private double performanceGlobale;
}
