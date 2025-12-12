package com.campusmaster.domaine.service;

import com.campusmaster.domaine.entite.Discussion;
import com.campusmaster.domaine.entite.MessageDiscussion;

import java.util.List;

public interface ServiceDiscussion {

    Discussion creerDiscussion(Long coursId, String sujet, Long auteurId);

    MessageDiscussion posterMessage(Long discussionId, Long auteurId, String contenu);

    List<MessageDiscussion> listerMessages(Long discussionId);
}
