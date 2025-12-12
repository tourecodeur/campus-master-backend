package com.campusmaster.domaine.entite;

import com.campusmaster.domaine.enums.TypeSupport;
import jakarta.persistence.*;
import lombok.*;

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
    private int version = 1;

    @Column(nullable = false)
    private LocalDateTime dateUpload = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;
}
