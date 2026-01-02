package com.campusmaster.domaine.entite;

import com.campusmaster.domaine.enums.TypeRole;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String motDePasse;

    @Column(nullable = false)
    private String nomComplet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRole role;

    @Column(nullable = false)
    @Builder.Default
    private boolean actif = true;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dateInscription = LocalDateTime.now();


@PrePersist
public void prePersist() {
    if (dateInscription == null) {
        dateInscription = LocalDateTime.now();
    }
}

}
