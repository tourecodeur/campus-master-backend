package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "depots_devoirs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepotDevoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlFichier;

    private int version;

    private LocalDateTime dateDepot;

    @ManyToOne
    @JoinColumn(name = "devoir_id")
    private Devoir devoir;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Utilisateur etudiant;

    @OneToOne(mappedBy = "depot", cascade = CascadeType.ALL)
    private NoteDevoir note;
}
