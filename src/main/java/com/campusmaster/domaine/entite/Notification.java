package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeEvenement; // DEVOIR_PUBLIE, DEVOIR_CORRIGE, NOUVEAU_MESSAGE, DEADLINE_PROCHE ...

    @Column(length = 1000)
    private String message;

    private boolean lu = false;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
