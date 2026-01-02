package com.campusmaster.domaine.entite;

import com.campusmaster.domaine.enums.TypeSupport;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Table(name = "supports_cours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSupport type;

    @Column(nullable = false)
    private String nomFichier;

    @Column(nullable = false)
    private String urlFichier;

    @Column(nullable = false)
    @Builder.Default
    private int version = 1;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dateUpload = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cours_id")
    @JsonIgnore
    private Cours cours;

    /**
     * Expose l'ID du cours dans les réponses JSON sans sérialiser l'entité Cours
     * (évite les boucles et permet au frontend de filtrer par cours).
     */
    @JsonProperty("coursId")
    public Long getCoursId() {
        return cours != null ? cours.getId() : null;
    }
}
