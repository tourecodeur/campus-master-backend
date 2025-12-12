package com.campusmaster.web.controller;

import com.campusmaster.domaine.entite.MessageDiscussion;
import com.campusmaster.domaine.entite.MessagePrive;
import com.campusmaster.domaine.service.ServiceDiscussion;
import com.campusmaster.domaine.service.ServiceMessageriePrivee;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Module de messagerie interne : privé et discussions de cours.
 */
@RestController
@RequestMapping("/api/messagerie")
@CrossOrigin
@Tag(name = "Messagerie")
public class MessagerieController {

    private final ServiceMessageriePrivee serviceMessageriePrivee;
    private final ServiceDiscussion serviceDiscussion;

    public MessagerieController(ServiceMessageriePrivee serviceMessageriePrivee,
                                ServiceDiscussion serviceDiscussion) {
        this.serviceMessageriePrivee = serviceMessageriePrivee;
        this.serviceDiscussion = serviceDiscussion;
    }

    @PostMapping("/privee")
    public ResponseEntity<MessagePrive> envoyerMessagePrive(@RequestParam Long expediteurId,
                                                            @RequestParam Long destinataireId,
                                                            @RequestParam String contenu,
                                                            @RequestParam(required = false) String tag) {
        return ResponseEntity.ok(serviceMessageriePrivee.envoyerMessage(expediteurId, destinataireId, contenu, tag));
    }

    @GetMapping("/privee/historique")
    public ResponseEntity<List<MessagePrive>> historique(@RequestParam Long u1,
                                                         @RequestParam Long u2) {
        return ResponseEntity.ok(serviceMessageriePrivee.historiqueConversation(u1, u2));
    }

    @PostMapping("/discussions/{discussionId}/messages")
    public ResponseEntity<MessageDiscussion> posterMessage(@PathVariable Long discussionId,
                                                           @RequestParam Long auteurId,
                                                           @RequestParam String contenu) {
        return ResponseEntity.ok(serviceDiscussion.posterMessage(discussionId, auteurId, contenu));
    }

    @GetMapping("/discussions/{discussionId}/messages")
    public ResponseEntity<List<MessageDiscussion>> listerMessages(@PathVariable Long discussionId) {
        return ResponseEntity.ok(serviceDiscussion.listerMessages(discussionId));
    }
}
