package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matieres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
