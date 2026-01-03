package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}
