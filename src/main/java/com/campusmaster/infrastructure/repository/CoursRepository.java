package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    List<Cours> findByEnseignant(Utilisateur enseignant);
}
