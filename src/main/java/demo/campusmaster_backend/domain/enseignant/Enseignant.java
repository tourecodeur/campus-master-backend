package demo.campusmaster_backend.domain.enseignant;

import demo.campusmaster_backend.domain.auth.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enseignants")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String titre; // M., Mme, Dr...
    private LocalDate dateEmbauche;
    private String matierePrincipale;
    private String statut;
    private String telephone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ===== GETTERS & SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
