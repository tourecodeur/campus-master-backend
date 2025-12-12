package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages_prives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagePrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private Utilisateur expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Utilisateur destinataire;

    @Column(length = 4000)
    private String contenu;

    private String tag; // #urgent, #annonce, #projet

    private LocalDateTime dateEnvoi = LocalDateTime.now();
}
