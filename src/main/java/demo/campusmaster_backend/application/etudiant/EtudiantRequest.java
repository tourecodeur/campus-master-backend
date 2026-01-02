package demo.campusmaster_backend.application.etudiant;
import demo.campusmaster_backend.domain.etudiant.Etudiant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class EtudiantRequest {

    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String niveau;
    private String filiere;
    private String statut;
    private String password;

    // ===== Getters et Setters =====
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

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

     public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
     public Etudiant toEntity() {
        Etudiant e = new Etudiant();
        e.setNom(this.nom);
        e.setPrenom(this.prenom);
        e.setEmail(this.email);
        e.setTelephone(this.telephone);
        e.setNiveau(this.niveau);
        e.setStatut(this.statut);
        return e;
    }
}


   

