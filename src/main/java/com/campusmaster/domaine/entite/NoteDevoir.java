package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes_devoirs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDevoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double note;

    @Column(length = 2000)
    private String commentaire;

    @OneToOne
    @JoinColumn(name = "depot_id")
    private DepotDevoir depot;
}
