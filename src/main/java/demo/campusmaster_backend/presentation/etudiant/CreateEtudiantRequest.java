package demo.campusmaster_backend.presentation.etudiant;
import demo.campusmaster_backend.domain.etudiant.Etudiant;
public class CreateEtudiantRequest {

    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String niveau;
    private String statut;
    private String password;

    // ===== GETTERS =====
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getMatricule(){return matricule;}
    public String getNiveau() { return niveau; }
    public String getStatut() { return statut; }
    public String getPassword() { return password; }

    // ===== SETTERS =====
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setNiveau(String niveau) { this.niveau = niveau; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setPassword(String password) { this.password = password; }
    
     public Etudiant toEntity() {
        Etudiant e = new Etudiant();
        e.setMatricule(this.matricule);
        e.setNom(this.nom);
        e.setPrenom(this.prenom);
        e.setEmail(this.email);
        e.setTelephone(this.telephone);
        e.setNiveau(this.niveau);
        e.setStatut(this.statut);
        return e;
    }
}
