package com.campusmaster.application.service;

import com.campusmaster.domaine.entite.Notification;

import java.util.List;

public interface ServiceNotification {

    void notifierDevoirPublie(Long devoirId);

    void notifierDevoirCorrige(Long depotId);

    void notifierNouveauMessagePrive(Long messageId);

    void notifierDeadlineProche(Long devoirId);

    List<Notification> listerNotificationsNonLues(Long utilisateurId);

    void marquerCommeLue(Long notificationId);
}
