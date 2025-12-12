package com.campusmaster.domaine.repository;

import com.campusmaster.domaine.entite.NoteDevoir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDevoirRepository extends JpaRepository<NoteDevoir, Long> {
}
