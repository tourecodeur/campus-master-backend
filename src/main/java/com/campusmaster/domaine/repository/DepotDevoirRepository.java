package com.campusmaster.domaine.repository;

import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotDevoirRepository extends JpaRepository<DepotDevoir, Long> {

    List<DepotDevoir> findByDevoirAndEtudiantOrderByVersionDesc(Devoir devoir, Utilisateur etudiant);

    long countByDevoir(Devoir devoir);
}
