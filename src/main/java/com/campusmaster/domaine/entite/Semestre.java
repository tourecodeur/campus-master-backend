package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semestres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code; // ex: S1, S2

    private String description;
}
