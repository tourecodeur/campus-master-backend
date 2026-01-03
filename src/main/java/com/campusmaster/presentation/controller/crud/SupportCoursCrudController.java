package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.SupportCours;
import com.campusmaster.infrastructure.repository.CoursRepository;
import com.campusmaster.infrastructure.repository.SupportCoursRepository;
// import com.campusmaster.domaine.repository.CoursRepository;
// import com.campusmaster.domaine.repository.SupportCoursRepository;
import com.campusmaster.presentation.dto.crud.SupportCoursRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supports-cours")
@CrossOrigin
@Tag(name = "Supports de cours")
public class SupportCoursCrudController {

    private final SupportCoursRepository supportRepository;
    private final CoursRepository coursRepository;

    public SupportCoursCrudController(SupportCoursRepository supportRepository, CoursRepository coursRepository) {
        this.supportRepository = supportRepository;
        this.coursRepository = coursRepository;
    }

    @GetMapping
    public ResponseEntity<List<SupportCours>> lister() {
        return ResponseEntity.ok(supportRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportCours> obtenir(@PathVariable Long id) {
        return supportRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupportCours> creer(@RequestBody SupportCoursRequest req) {
        SupportCours s = new SupportCours();
        s.setNomFichier(req.getNomFichier() != null ? req.getNomFichier() : req.getTitre());
        s.setType(req.getType());
        s.setUrlFichier(req.getUrlFichier());
        if (req.getVersion() != null)
            s.setVersion(req.getVersion());
        if (req.getCoursId() != null) {
            Cours c = coursRepository.findById(req.getCoursId())
                    .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
            s.setCours(c);
        }
        return ResponseEntity.ok(supportRepository.save(s));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportCours> modifier(@PathVariable Long id, @RequestBody SupportCoursRequest req) {
        return supportRepository.findById(id).map(existing -> {
            // Le nom du fichier est porté par nomFichier (titre est conservé pour compat
            // éventuelle)
            if (req.getNomFichier() != null || req.getTitre() != null) {
                existing.setNomFichier(req.getNomFichier() != null ? req.getNomFichier() : req.getTitre());
            }
            if (req.getType() != null)
                existing.setType(req.getType());
            if (req.getUrlFichier() != null)
                existing.setUrlFichier(req.getUrlFichier());
            if (req.getVersion() != null)
                existing.setVersion(req.getVersion());
            if (req.getCoursId() != null) {
                Cours c = coursRepository.findById(req.getCoursId())
                        .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
                existing.setCours(c);
            }
            return ResponseEntity.ok(supportRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!supportRepository.existsById(id))
            return ResponseEntity.notFound().build();
        supportRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
