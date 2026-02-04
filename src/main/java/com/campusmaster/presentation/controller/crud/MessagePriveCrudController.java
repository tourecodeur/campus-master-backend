package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.MessagePrive;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.MessagePriveRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.MessagePriveRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.crud.MessagePriveRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages-prives")
@CrossOrigin
@Tag(name = "Messagerie privée")
public class MessagePriveCrudController {

    private final MessagePriveRepository messageRepository;
    private final UtilisateurRepository utilisateurRepository;

    public MessagePriveCrudController(MessagePriveRepository messageRepository,
            UtilisateurRepository utilisateurRepository) {
        this.messageRepository = messageRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<MessagePrive>> lister() {
        return ResponseEntity.ok(messageRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessagePrive> obtenir(@PathVariable Long id) {
        return messageRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MessagePrive> creer(@RequestBody MessagePriveRequest req) {
        MessagePrive m = new MessagePrive();
        m.setContenu(req.getContenu());
        m.setTag(req.getTag());

        if (req.getExpediteurId() != null) {
            Utilisateur u = utilisateurRepository.findById(req.getExpediteurId())
                    .orElseThrow(() -> new IllegalArgumentException("Expéditeur introuvable"));
            m.setExpediteur(u);
        }
        if (req.getDestinataireId() != null) {
            Utilisateur u = utilisateurRepository.findById(req.getDestinataireId())
                    .orElseThrow(() -> new IllegalArgumentException("Destinataire introuvable"));
            m.setDestinataire(u);
        }
        return ResponseEntity.ok(messageRepository.save(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePrive> modifier(@PathVariable Long id, @RequestBody MessagePriveRequest req) {
        return messageRepository.findById(id).map(existing -> {
            if (req.getContenu() != null)
                existing.setContenu(req.getContenu());
            if (req.getTag() != null)
                existing.setTag(req.getTag());
            if (req.getExpediteurId() != null) {
                Utilisateur u = utilisateurRepository.findById(req.getExpediteurId())
                        .orElseThrow(() -> new IllegalArgumentException("Expéditeur introuvable"));
                existing.setExpediteur(u);
            }
            if (req.getDestinataireId() != null) {
                Utilisateur u = utilisateurRepository.findById(req.getDestinataireId())
                        .orElseThrow(() -> new IllegalArgumentException("Destinataire introuvable"));
                existing.setDestinataire(u);
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
