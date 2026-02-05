package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Notification;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.NotificationRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.NotificationRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.crud.NotificationRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin
@Tag(name = "Notifications")
public class NotificationCrudController {

    private final NotificationRepository notificationRepository;
    private final UtilisateurRepository utilisateurRepository;

    public NotificationCrudController(NotificationRepository notificationRepository,
            UtilisateurRepository utilisateurRepository) {
        this.notificationRepository = notificationRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> lister() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> obtenir(@PathVariable Long id) {
        return notificationRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notification> creer(@RequestBody NotificationRequest req) {
        Notification n = new Notification();
        n.setTypeEvenement(req.getTypeEvenement());
        n.setMessage(req.getMessage());
        if (req.getLu() != null)
            n.setLu(req.getLu());
        if (req.getUtilisateurId() != null) {
            Utilisateur u = utilisateurRepository.findById(req.getUtilisateurId())
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
            n.setUtilisateur(u);
        }
        return ResponseEntity.ok(notificationRepository.save(n));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> modifier(@PathVariable Long id, @RequestBody NotificationRequest req) {
        return notificationRepository.findById(id).map(existing -> {
            if (req.getTypeEvenement() != null)
                existing.setTypeEvenement(req.getTypeEvenement());
            if (req.getMessage() != null)
                existing.setMessage(req.getMessage());
            if (req.getLu() != null)
                existing.setLu(req.getLu());
            if (req.getUtilisateurId() != null) {
                Utilisateur u = utilisateurRepository.findById(req.getUtilisateurId())
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
                existing.setUtilisateur(u);
            }
            return ResponseEntity.ok(notificationRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!notificationRepository.existsById(id))
            return ResponseEntity.notFound().build();
        notificationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
