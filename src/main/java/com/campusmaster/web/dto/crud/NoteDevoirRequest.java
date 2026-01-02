package com.campusmaster.web.dto.crud;

import lombok.Data;

@Data
public class NoteDevoirRequest {
    private Long depotId;
    private Long devoirId;
    private Long etudiantId;
    private Double note;
    private String commentaire;
}
