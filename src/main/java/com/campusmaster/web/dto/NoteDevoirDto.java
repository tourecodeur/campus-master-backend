package com.campusmaster.web.dto;

import lombok.Data;

@Data
public class NoteDevoirDto {

    private Long depotId;
    private Double note;
    private String commentaire;
    private String devoirTitre;
}
