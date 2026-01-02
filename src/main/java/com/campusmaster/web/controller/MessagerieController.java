package com.campusmaster.web.controller;

import com.campusmaster.domaine.entite.MessageDiscussion;
import com.campusmaster.domaine.entite.MessagePrive;
import com.campusmaster.domaine.service.ServiceDiscussion;
import com.campusmaster.domaine.service.ServiceMessageriePrivee;
import com.campusmaster.security.CurrentUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messagerie")
@CrossOrigin
@Tag(name = "Messagerie")
public class MessagerieController {

    private final ServiceMessageriePrivee serviceMessageriePrivee;
    private final ServiceDiscussion serviceDiscussion;
    private final CurrentUserService currentUserService;

    public MessagerieController(ServiceMessageriePrivee serviceMessageriePrivee,
                                ServiceDiscussion serviceDiscussion,
                                CurrentUserService currentUserService) {
        this.serviceMessageriePrivee = serviceMessageriePrivee;
        this.serviceDiscussion = serviceDiscussion;
        this.currentUserService = currentUserService;
    }

    /**
     * Version recommandée: l'utilisateur connecté envoie à un autre utilisateur.
     */
    @PostMapping("/me/prive/envoyer")
    public ResponseEntity<MessagePrive> envoyerPrive(@RequestParam Long destinataireId,
                                                     @RequestParam String contenu) {
        Long expediteurId = currentUserService.getCurrentUtilisateur().getId();
        return ResponseEntity.ok(serviceMessageriePrivee.envoyerMessage(expediteurId, destinataireId, contenu, "PRIVE"));
    }

    @GetMapping("/me/prive/historique")
    public ResponseEntity<List<MessagePrive>> historiqueAvec(@RequestParam Long avecUtilisateurId) {
        Long me = currentUserService.getCurrentUtilisateur().getId();
        return ResponseEntity.ok(serviceMessageriePrivee.historiqueConversation(me, avecUtilisateurId));
    }

    /**
     * Compatibilité : endpoints historiques.
     * Sécurisé : seuls les participants (u1 ou u2) ou ADMIN peuvent consulter.
     */
    @PostMapping("/privee")
    public ResponseEntity<MessagePrive> envoyerMessagePrive(@RequestParam Long expediteurId,
                                                            @RequestParam Long destinataireId,
                                                            @RequestParam String contenu) {
        currentUserService.assertSelfOrAdmin(expediteurId);
        return ResponseEntity.ok(serviceMessageriePrivee.envoyerMessage(expediteurId, destinataireId, contenu, "PRIVE"));
    }

    @GetMapping("/privee/historique")
    public ResponseEntity<List<MessagePrive>> historiqueConversation(@RequestParam Long u1,
                                                                     @RequestParam Long u2) {
        if (!currentUserService.isAdmin()) {
            Long me = currentUserService.getCurrentUtilisateur().getId();
            if (!me.equals(u1) && !me.equals(u2)) {
                throw new org.springframework.security.access.AccessDeniedException("Accès interdit : conversation d'autres utilisateurs");
            }
        }
        return ResponseEntity.ok(serviceMessageriePrivee.historiqueConversation(u1, u2));
    }

    @PostMapping("/discussions/{discussionId}/messages")
    public ResponseEntity<MessageDiscussion> posterMessage(@PathVariable Long discussionId,
                                                           @RequestParam Long auteurId,
                                                           @RequestParam String contenu) {
        currentUserService.assertSelfOrAdmin(auteurId);
        return ResponseEntity.ok(serviceDiscussion.posterMessage(discussionId, auteurId, contenu));
    }

    @GetMapping("/discussions/{discussionId}/messages")
    public ResponseEntity<List<MessageDiscussion>> listerMessages(@PathVariable Long discussionId) {
        return ResponseEntity.ok(serviceDiscussion.listerMessages(discussionId));
    }
}
