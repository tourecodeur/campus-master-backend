package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.enums.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Utilisateur> findByRole(TypeRole role);
}
