package com.campusmaster.web.dto.crud;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DevoirRequest {
    private String titre;
    private String consigne;
    private LocalDateTime dateLimite;
    private Long coursId;
}
