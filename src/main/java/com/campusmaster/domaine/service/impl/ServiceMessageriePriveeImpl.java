package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.MessagePrive;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.repository.MessagePriveRepository;
import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.domaine.service.ServiceMessageriePrivee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMessageriePriveeImpl implements ServiceMessageriePrivee {

    private final MessagePriveRepository messagePriveRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ServiceMessageriePriveeImpl(MessagePriveRepository messagePriveRepository,
                                       UtilisateurRepository utilisateurRepository) {
        this.messagePriveRepository = messagePriveRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public MessagePrive envoyerMessage(Long expediteurId, Long destinataireId, String contenu, String tag) {
        Utilisateur expediteur = utilisateurRepository.findById(expediteurId)
                .orElseThrow(() -> new IllegalArgumentException("Expéditeur introuvable"));
        Utilisateur destinataire = utilisateurRepository.findById(destinataireId)
                .orElseThrow(() -> new IllegalArgumentException("Destinataire introuvable"));

        MessagePrive message = MessagePrive.builder()
                .expediteur(expediteur)
                .destinataire(destinataire)
                .contenu(contenu)
                .tag(tag)
                .build();
        return messagePriveRepository.save(message);
    }

    @Override
    public List<MessagePrive> historiqueConversation(Long utilisateur1Id, Long utilisateur2Id) {
        Utilisateur u1 = utilisateurRepository.findById(utilisateur1Id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        Utilisateur u2 = utilisateurRepository.findById(utilisateur2Id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

        List<MessagePrive> list = new ArrayList<>();
        list.addAll(messagePriveRepository.findByExpediteurAndDestinataireOrderByDateEnvoiAsc(u1, u2));
        list.addAll(messagePriveRepository.findByExpediteurAndDestinataireOrderByDateEnvoiAsc(u2, u1));
        return list;
    }
}
