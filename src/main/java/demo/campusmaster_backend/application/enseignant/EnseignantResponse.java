package demo.campusmaster_backend.application.enseignant;

import java.time.LocalDate;

public class EnseignantResponse {

    private Long id;
    private String nom;
    private String prenom;
    private String titre;
    private String email;
    private String telephone;
    private String matierePrincipale;
    private LocalDate dateEmbauche;
    private String statut;

    public EnseignantResponse() {}

    public EnseignantResponse(Long id, String nom, String prenom, String titre, String email, String telephone,
                            String matierePrincipale, LocalDate dateEmbauche,String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.titre =titre;
        this.email = email;
        this.telephone = telephone;
        this.dateEmbauche = dateEmbauche;
        this.matierePrincipale = matierePrincipale;
        this.statut = statut;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public String getMatierePrincipale() { return matierePrincipale; }
    public void setMatierePrincipale(String matierePrincipale) { this.matierePrincipale = matierePrincipale; }

  
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
