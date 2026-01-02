package com.campusmaster.web.dto.crud;

import lombok.Data;

@Data
public class MessagePriveRequest {
    private Long expediteurId;
    private Long destinataireId;
    private String contenu;
    private String tag;
}
