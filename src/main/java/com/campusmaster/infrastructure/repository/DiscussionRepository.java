package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    List<Discussion> findByCours(Cours cours);
}
