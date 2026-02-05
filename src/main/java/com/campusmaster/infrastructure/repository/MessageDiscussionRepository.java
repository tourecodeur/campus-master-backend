package com.campusmaster.infrastructure.repository;

import com.campusmaster.domaine.entite.MessageDiscussion;
import com.campusmaster.domaine.entite.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDiscussionRepository extends JpaRepository<MessageDiscussion, Long> {

    List<MessageDiscussion> findByDiscussionOrderByDateEnvoiAsc(Discussion discussion);
}
