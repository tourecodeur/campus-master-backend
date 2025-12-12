package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.*;
import com.campusmaster.domaine.repository.CoursRepository;
import com.campusmaster.domaine.repository.DiscussionRepository;
import com.campusmaster.domaine.repository.MessageDiscussionRepository;
import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.domaine.service.ServiceDiscussion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDiscussionImpl implements ServiceDiscussion {

    private final DiscussionRepository discussionRepository;
    private final MessageDiscussionRepository messageDiscussionRepository;
    private final CoursRepository coursRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ServiceDiscussionImpl(DiscussionRepository discussionRepository,
                                 MessageDiscussionRepository messageDiscussionRepository,
                                 CoursRepository coursRepository,
                                 UtilisateurRepository utilisateurRepository) {
        this.discussionRepository = discussionRepository;
        this.messageDiscussionRepository = messageDiscussionRepository;
        this.coursRepository = coursRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Discussion creerDiscussion(Long coursId, String sujet, Long auteurId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
        Discussion discussion = Discussion.builder()
                .cours(cours)
                .sujet(sujet)
                .build();
        return discussionRepository.save(discussion);
    }

    @Override
    public MessageDiscussion posterMessage(Long discussionId, Long auteurId, String contenu) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion introuvable"));
        Utilisateur auteur = utilisateurRepository.findById(auteurId)
                .orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
        MessageDiscussion message = MessageDiscussion.builder()
                .discussion(discussion)
                .auteur(auteur)
                .contenu(contenu)
                .build();
        return messageDiscussionRepository.save(message);
    }

    @Override
    public List<MessageDiscussion> listerMessages(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion introuvable"));
        return messageDiscussionRepository.findByDiscussionOrderByDateEnvoiAsc(discussion);
    }
}
