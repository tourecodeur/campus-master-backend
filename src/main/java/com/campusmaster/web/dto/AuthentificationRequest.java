package com.campusmaster.web.dto;

import lombok.Data;

/**
 * Requête d'authentification (connexion).
 */
@Data
public class AuthentificationRequest {

    private String email;
    private String motDePasse;
}
