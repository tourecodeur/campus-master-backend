package com.campusmaster.web.dto.crud;

import lombok.Data;

@Data
public class NotificationRequest {
    private String typeEvenement;
    private String message;
    private Boolean lu;
    private Long utilisateurId;
}
