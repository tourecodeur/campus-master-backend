package com.campusmaster.web.dto.crud;

import com.campusmaster.domaine.enums.TypeSupport;
import lombok.Data;

@Data
public class SupportCoursRequest {
    /**
     * Ancien champ utilisé par certaines intégrations (mappé sur nomFichier).
     */
    private String titre;
    private String nomFichier;
    private TypeSupport type;
    private String urlFichier;
    private Integer version;
    private Long coursId;
}
