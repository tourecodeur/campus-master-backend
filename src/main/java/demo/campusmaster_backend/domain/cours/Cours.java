package demo.campusmaster_backend.domain.cours;

import jakarta.persistence.*;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;
@Column(name = "titre")
private String titre;

    @Column(length = 500)
    private String description;
    private String niveau;
    private String enseignant_id;
    private String semestre_id;
    private int credits;

    @Column(name = "etudiants_inscrits")
    private int etudiantsInscrits;

    private int heures;  // <-- Champ ajouté

    private String salle;
    private String matiere_id;

    private String statut;

    // ==================== Constructeurs ====================
    public Cours() {}

    public Cours(String code,String titre, String description, String niveau, String enseignant_id, String semestre_id, int credits, int etudiantsInscrits, int heures, String salle, String matiere_id, String statut) {
        this.code = code;
        this.titre=titre;
        this.description = description;
        this.niveau = niveau;
        this.enseignant_id = enseignant_id;
        this.semestre_id = semestre_id;
        this.credits = credits;
        this.etudiantsInscrits = etudiantsInscrits;
        this.heures = heures;
        this.salle = salle;
        this.matiere_id = matiere_id;
        this.statut = statut;
    }

    // ==================== Getters et Setters ====================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getEnseignant_id() { return enseignant_id; }
    public void setEnseignant_id(String enseignant_id) { this.enseignant_id = enseignant_id; }

    public String getSemestre_id() { return semestre_id; }
    public void setSemestre_id(String semestre_id) { this.semestre_id = semestre_id; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getEtudiantsInscrits() { return etudiantsInscrits; }
    public void setEtudiantsInscrits(int etudiantsInscrits) { this.etudiantsInscrits = etudiantsInscrits; }

    public int getHeures() { return heures; }
    public void setHeures(int heures) { this.heures = heures; }

    public String getSalle() { return salle; }
    public void setSalle(String salle) { this.salle = salle; }

    public String getMatiere_id() { return matiere_id; }
    public void setMatiere_id(String matiere_id) { this.matiere_id = matiere_id; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
