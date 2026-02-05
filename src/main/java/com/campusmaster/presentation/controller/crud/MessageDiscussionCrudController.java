package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Discussion;
import com.campusmaster.domaine.entite.MessageDiscussion;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.DiscussionRepository;
import com.campusmaster.infrastructure.repository.MessageDiscussionRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.DiscussionRepository;
// import com.campusmaster.domaine.repository.MessageDiscussionRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.crud.MessageDiscussionRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages-discussion")
@CrossOrigin
@Tag(name = "Messages de discussion")
public class MessageDiscussionCrudController {

    private final MessageDiscussionRepository messageRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final DiscussionRepository discussionRepository;

    public MessageDiscussionCrudController(MessageDiscussionRepository messageRepository,
            UtilisateurRepository utilisateurRepository,
            DiscussionRepository discussionRepository) {
        this.messageRepository = messageRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.discussionRepository = discussionRepository;
    }

    @GetMapping
    public ResponseEntity<List<MessageDiscussion>> lister() {
        return ResponseEntity.ok(messageRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDiscussion> obtenir(@PathVariable Long id) {
        return messageRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MessageDiscussion> creer(@RequestBody MessageDiscussionRequest req) {
        MessageDiscussion m = new MessageDiscussion();
        m.setContenu(req.getContenu());

        if (req.getAuteurId() != null) {
            Utilisateur auteur = utilisateurRepository.findById(req.getAuteurId())
                    .orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
            m.setAuteur(auteur);
        }
        if (req.getDiscussionId() != null) {
            Discussion d = discussionRepository.findById(req.getDiscussionId())
                    .orElseThrow(() -> new IllegalArgumentException("Discussion introuvable"));
            m.setDiscussion(d);
        }
        return ResponseEntity.ok(messageRepository.save(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDiscussion> modifier(@PathVariable Long id,
            @RequestBody MessageDiscussionRequest req) {
        return messageRepository.findById(id).map(existing -> {
            if (req.getContenu() != null)
                existing.setContenu(req.getContenu());
            if (req.getAuteurId() != null) {
                Utilisateur auteur = utilisateurRepository.findById(req.getAuteurId())
                        .orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
                existing.setAuteur(auteur);
            }
            if (req.getDiscussionId() != null) {
                Discussion d = discussionRepository.findById(req.getDiscussionId())
                        .orElseThrow(() -> new IllegalArgumentException("Discussion introuvable"));
                existing.setDiscussion(d);
            }
            return ResponseEntity.ok(messageRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!messageRepository.existsById(id))
            return ResponseEntity.notFound().build();
        messageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
