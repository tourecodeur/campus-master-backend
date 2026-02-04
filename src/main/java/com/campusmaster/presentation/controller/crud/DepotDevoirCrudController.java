package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.DepotDevoirRepository;
import com.campusmaster.infrastructure.repository.DevoirRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.DepotDevoirRepository;
// import com.campusmaster.domaine.repository.DevoirRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.crud.DepotDevoirRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/depots-devoir")
@CrossOrigin
@Tag(name = "Dépôts de devoir")
public class DepotDevoirCrudController {

    private final DepotDevoirRepository depotRepository;
    private final DevoirRepository devoirRepository;
    private final UtilisateurRepository utilisateurRepository;

    public DepotDevoirCrudController(DepotDevoirRepository depotRepository,
            DevoirRepository devoirRepository,
            UtilisateurRepository utilisateurRepository) {
        this.depotRepository = depotRepository;
        this.devoirRepository = devoirRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<DepotDevoir>> lister() {
        return ResponseEntity.ok(depotRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepotDevoir> obtenir(@PathVariable Long id) {
        return depotRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepotDevoir> creer(@RequestBody DepotDevoirRequest req) {
        DepotDevoir d = new DepotDevoir();
        d.setUrlFichier(req.getUrlFichier());
        if (req.getDateDepot() != null)
            d.setDateDepot(req.getDateDepot());

        if (req.getDevoirId() != null) {
            Devoir devoir = devoirRepository.findById(req.getDevoirId())
                    .orElseThrow(() -> new IllegalArgumentException("Devoir introuvable"));
            d.setDevoir(devoir);
        }
        if (req.getEtudiantId() != null) {
            Utilisateur etudiant = utilisateurRepository.findById(req.getEtudiantId())
                    .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));
            d.setEtudiant(etudiant);
        }
        return ResponseEntity.ok(depotRepository.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepotDevoir> modifier(@PathVariable Long id, @RequestBody DepotDevoirRequest req) {
        return depotRepository.findById(id).map(existing -> {
            if (req.getUrlFichier() != null)
                existing.setUrlFichier(req.getUrlFichier());
            if (req.getDateDepot() != null)
                existing.setDateDepot(req.getDateDepot());
            if (req.getDevoirId() != null) {
                Devoir devoir = devoirRepository.findById(req.getDevoirId())
                        .orElseThrow(() -> new IllegalArgumentException("Devoir introuvable"));
                existing.setDevoir(devoir);
            }
            if (req.getEtudiantId() != null) {
                Utilisateur etudiant = utilisateurRepository.findById(req.getEtudiantId())
                        .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));
                existing.setEtudiant(etudiant);
            }
            return ResponseEntity.ok(depotRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!depotRepository.existsById(id))
            return ResponseEntity.notFound().build();
        depotRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
