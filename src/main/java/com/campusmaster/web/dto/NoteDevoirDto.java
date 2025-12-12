package com.campusmaster.web.dto;

import lombok.Data;

/**
 * DTO pour retourner la note et le feedback d'un devoir.
 */
@Data
public class NoteDevoirDto {

    private Long depotId;
    private Double note;
    private String commentaire;
    private String devoirTitre;
}
