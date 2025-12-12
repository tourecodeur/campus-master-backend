package com.campusmaster.domaine.service;

import com.campusmaster.domaine.entite.MessagePrive;

import java.util.List;

public interface ServiceMessageriePrivee {

    MessagePrive envoyerMessage(Long expediteurId, Long destinataireId, String contenu, String tag);

    List<MessagePrive> historiqueConversation(Long utilisateur1Id, Long utilisateur2Id);
}
