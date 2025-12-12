package com.campusmaster.web.dto;

import com.campusmaster.domaine.enums.TypeRole;
import lombok.Data;

/**
 * Requête d'inscription d'un utilisateur (étudiant, enseignant, admin).
 */
@Data
public class InscriptionRequest {

    private String email;
    private String motDePasse;
    private String nomComplet;
    private TypeRole role;
}
