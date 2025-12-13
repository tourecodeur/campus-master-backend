package com.campusmaster.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatistiquesTableauDeBordDto {

    private long nombreEtudiantsActifs;
    private double tauxRemiseDevoirs;
    private double performanceGlobale;
}
