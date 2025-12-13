package com.campusmaster.web.dto;

import com.campusmaster.domaine.enums.TypeRole;
import lombok.Data;

@Data
public class InscriptionRequest {

    private String email;
    private String motDePasse;
    private String nomComplet;
    private TypeRole role;
}
