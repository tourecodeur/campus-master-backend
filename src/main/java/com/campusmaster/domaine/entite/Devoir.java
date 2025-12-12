package com.campusmaster.domaine.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "devoirs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 4000)
    private String consigne;

    @Column(nullable = false)
    private LocalDateTime dateLimite;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @OneToMany(mappedBy = "devoir", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DepotDevoir> depots = new HashSet<>();
}
