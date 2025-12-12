package com.campusmaster.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Réponse après authentification contenant le token JWT et le rôle.
 */
@Data
@AllArgsConstructor
public class AuthentificationResponse {

    private String token;
    private String role;
}
