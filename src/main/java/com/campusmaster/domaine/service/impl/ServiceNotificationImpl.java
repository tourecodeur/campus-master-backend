package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.*;
import com.campusmaster.domaine.repository.*;
import com.campusmaster.domaine.service.ServiceNotification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNotificationImpl implements ServiceNotification {

    private final NotificationRepository notificationRepository;
    private final DevoirRepository devoirRepository;
    private final DepotDevoirRepository depotDevoirRepository;
    private final MessagePriveRepository messagePriveRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ServiceNotificationImpl(NotificationRepository notificationRepository,
                                   DevoirRepository devoirRepository,
                                   DepotDevoirRepository depotDevoirRepository,
                                   MessagePriveRepository messagePriveRepository,
                                   UtilisateurRepository utilisateurRepository) {
        this.notificationRepository = notificationRepository;
        this.devoirRepository = devoirRepository;
        this.depotDevoirRepository = depotDevoirRepository;
        this.messagePriveRepository = messagePriveRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void notifierDevoirPublie(Long devoirId) {
        // Hook à implémenter selon la logique métier (liste d'étudiants inscrits).
    }

    @Override
    public void notifierDevoirCorrige(Long depotId) {
        DepotDevoir depot = depotDevoirRepository.findById(depotId)
                .orElseThrow(() -> new IllegalArgumentException("Dépôt introuvable"));
        Notification notification = Notification.builder()
                .utilisateur(depot.getEtudiant())
                .typeEvenement("DEVOIR_CORRIGE")
                .message("Votre devoir '" + depot.getDevoir().getTitre() + "' a été corrigé.")
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public void notifierNouveauMessagePrive(Long messageId) {
        MessagePrive message = messagePriveRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message privé introuvable"));
        Notification notification = Notification.builder()
                .utilisateur(message.getDestinataire())
                .typeEvenement("NOUVEAU_MESSAGE")
                .message("Nouveau message de " + message.getExpediteur().getNomComplet())
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public void notifierDeadlineProche(Long devoirId) {
        // À implémenter plus tard (notifications de deadline).
    }

    @Override
    public List<Notification> listerNotificationsNonLues(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        return notificationRepository.findByUtilisateurAndLuIsFalse(utilisateur);
    }

    @Override
    public void marquerCommeLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification introuvable"));
        notification.setLu(true);
        notificationRepository.save(notification);
    }
}
