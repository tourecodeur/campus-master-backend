package com.campusmaster.domaine.repository;

import com.campusmaster.domaine.entite.SupportCours;
import com.campusmaster.domaine.entite.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportCoursRepository extends JpaRepository<SupportCours, Long> {

    List<SupportCours> findByCours(Cours cours);
}
