package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevoirRepository extends JpaRepository<Devoir, Long> {

    List<Devoir> findByCours(Cours cours);
}
