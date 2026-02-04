package com.campusmaster.presentation.dto;

import lombok.Data;

@Data
public class AuthentificationRequest {

    private String email;
    private String motDePasse;
}
