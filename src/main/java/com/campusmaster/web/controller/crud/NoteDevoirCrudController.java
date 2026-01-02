package com.campusmaster.web.controller.crud;

import com.campusmaster.domaine.entite.*;
import com.campusmaster.domaine.repository.*;
import com.campusmaster.web.dto.crud.NoteDevoirRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes-devoir")
@CrossOrigin
@Tag(name = "Notes devoir (CRUD)")
public class NoteDevoirCrudController {

    private final NoteDevoirRepository noteRepository;
    private final DepotDevoirRepository depotRepository;
    private final DevoirRepository devoirRepository;
    private final UtilisateurRepository utilisateurRepository;

    public NoteDevoirCrudController(
            NoteDevoirRepository noteRepository,
            DepotDevoirRepository depotRepository,
            DevoirRepository devoirRepository,
            UtilisateurRepository utilisateurRepository
    ) {
        this.noteRepository = noteRepository;
        this.depotRepository = depotRepository;
        this.devoirRepository = devoirRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public List<NoteDevoir> lister() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDevoir> obtenir(@PathVariable Long id) {
        return noteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NoteDevoir> creer(@RequestBody NoteDevoirRequest req) {
        NoteDevoir n = new NoteDevoir();
        n.setNote(req.getNote());
        n.setCommentaire(req.getCommentaire());

        DepotDevoir depot = resolveDepot(req);
        n.setDepot(depot);

        return ResponseEntity.ok(noteRepository.save(n));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDevoir> modifier(@PathVariable Long id, @RequestBody NoteDevoirRequest req) {
        return noteRepository.findById(id).map(existing -> {
            if (req.getNote() != null) existing.setNote(req.getNote());
            if (req.getCommentaire() != null) existing.setCommentaire(req.getCommentaire());

            // Autoriser changement de dépôt si demandé
            if (req.getDepotId() != null || (req.getDevoirId() != null && req.getEtudiantId() != null)) {
                DepotDevoir depot = resolveDepot(req);
                existing.setDepot(depot);
            }

            return ResponseEntity.ok(noteRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!noteRepository.existsById(id)) return ResponseEntity.notFound().build();
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private DepotDevoir resolveDepot(NoteDevoirRequest req) {
        if (req.getDepotId() != null) {
            return depotRepository.findById(req.getDepotId())
                    .orElseThrow(() -> new IllegalArgumentException("Dépôt de devoir introuvable"));
        }

        if (req.getDevoirId() != null && req.getEtudiantId() != null) {
            Devoir devoir = devoirRepository.findById(req.getDevoirId())
                    .orElseThrow(() -> new IllegalArgumentException("Devoir introuvable"));
            Utilisateur etudiant = utilisateurRepository.findById(req.getEtudiantId())
                    .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));

            List<DepotDevoir> depots = depotRepository.findByDevoirAndEtudiantOrderByVersionDesc(devoir, etudiant);
            if (depots == null || depots.isEmpty()) {
                throw new IllegalArgumentException("Aucun dépôt trouvé pour ce devoir et cet étudiant");
            }
            return depots.get(0);
        }

        throw new IllegalArgumentException("Veuillez fournir depotId, ou (devoirId + etudiantId).");
    }
}
