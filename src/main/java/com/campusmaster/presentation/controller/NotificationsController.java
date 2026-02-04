package com.campusmaster.presentation.controller;

import com.campusmaster.application.service.ServiceNotification;
import com.campusmaster.domaine.entite.Notification;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
@Tag(name = "Notifications")
public class NotificationsController {

    private final ServiceNotification serviceNotification;

    public NotificationsController(ServiceNotification serviceNotification) {
        this.serviceNotification = serviceNotification;
    }

    @GetMapping("/{utilisateurId}")
    public ResponseEntity<List<Notification>> lister(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(serviceNotification.listerNotificationsNonLues(utilisateurId));
    }

    @PostMapping("/{notificationId}/lue")
    public ResponseEntity<Void> marquerCommeLue(@PathVariable Long notificationId) {
        serviceNotification.marquerCommeLue(notificationId);
        return ResponseEntity.noContent().build();
    }
}
