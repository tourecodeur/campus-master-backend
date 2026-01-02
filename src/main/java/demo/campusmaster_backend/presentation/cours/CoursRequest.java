package demo.campusmaster_backend.presentation.cours;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CoursRequest {

    @NotBlank
    public String code;

    @NotBlank
    public String titre;

    public String description;

    @NotBlank
    public String matiere;

    // 🔗 CLÉS ÉTRANGÈRES
    @NotNull
    public Long enseignant_id;

    @NotNull
    public Long semestre_id;

    @NotBlank
    public String niveau;

    @Min(1)
    public int credits;

    @Min(1)
    public int heures;

    public String salle;

    @NotBlank
    public String statut;

    @Min(0)
    public int etudiantsInscrits;
}
