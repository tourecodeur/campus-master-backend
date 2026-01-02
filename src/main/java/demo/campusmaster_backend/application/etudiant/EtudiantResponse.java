package demo.campusmaster_backend.application.etudiant;

import java.time.LocalDate;

public class EtudiantResponse {

    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String telephone;
    private String niveau;
    private String statut;

    public EtudiantResponse() {}

    public EtudiantResponse(Long id, String nom, String prenom, String matricule, String email, String telephone,
                            String niveau,String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule =matricule;
        this.email = email;
        this.telephone = telephone;
        this.niveau = niveau;
        this.statut = statut;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }


  

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
