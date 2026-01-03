package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
