package com.campusmaster.web.dto;

import lombok.Data;

@Data
public class AuthentificationRequest {

    private String email;
    private String motDePasse;
}
