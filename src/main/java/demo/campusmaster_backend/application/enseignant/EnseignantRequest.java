package demo.campusmaster_backend.application.enseignant;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import demo.campusmaster_backend.domain.enseignant.Enseignant;

public class EnseignantRequest {

    private String nom;
    private String prenom;
    private String email;
    private String titre;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;

    private String matierePrincipale;
    private String statut;
    private String telephone;
    private String password;

    // ===== GETTERS & SETTERS =====
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public String getMatierePrincipale() { return matierePrincipale; }
    public void setMatierePrincipale(String matierePrincipale) { this.matierePrincipale = matierePrincipale; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // ===== CONVERSION DTO → ENTITY =====
    public Enseignant toEntity() {
        Enseignant e = new Enseignant();
        e.setNom(this.nom);
        e.setPrenom(this.prenom);
        e.setEmail(this.email);
        e.setTitre(this.titre);
        e.setDateEmbauche(this.dateEmbauche);
        e.setMatierePrincipale(this.matierePrincipale);
        e.setStatut(this.statut);
        e.setTelephone(this.telephone);
        return e;
    }
}
