package com.campusmaster.web.dto.crud;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepotDevoirRequest {
    private Long devoirId;
    private Long etudiantId;
    private String urlFichier;
    private LocalDateTime dateDepot;
}
