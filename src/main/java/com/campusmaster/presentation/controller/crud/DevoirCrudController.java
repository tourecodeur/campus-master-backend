package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.infrastructure.repository.CoursRepository;
import com.campusmaster.infrastructure.repository.DevoirRepository;
// import com.campusmaster.domaine.repository.CoursRepository;
// import com.campusmaster.domaine.repository.DevoirRepository;
import com.campusmaster.presentation.dto.crud.DevoirRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devoirs")
@CrossOrigin
@Tag(name = "Devoirs")
public class DevoirCrudController {

    private final DevoirRepository devoirRepository;
    private final CoursRepository coursRepository;

    public DevoirCrudController(DevoirRepository devoirRepository, CoursRepository coursRepository) {
        this.devoirRepository = devoirRepository;
        this.coursRepository = coursRepository;
    }

    @GetMapping
    public ResponseEntity<List<Devoir>> lister() {
        return ResponseEntity.ok(devoirRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devoir> obtenir(@PathVariable Long id) {
        return devoirRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devoir> creer(@RequestBody DevoirRequest req) {
        Devoir d = new Devoir();
        d.setTitre(req.getTitre());
        d.setConsigne(req.getConsigne());
        d.setDateLimite(req.getDateLimite());
        if (req.getCoursId() != null) {
            Cours c = coursRepository.findById(req.getCoursId())
                    .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
            d.setCours(c);
        }
        return ResponseEntity.ok(devoirRepository.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devoir> modifier(@PathVariable Long id, @RequestBody DevoirRequest req) {
        return devoirRepository.findById(id).map(existing -> {
            if (req.getTitre() != null)
                existing.setTitre(req.getTitre());
            if (req.getConsigne() != null)
                existing.setConsigne(req.getConsigne());
            if (req.getDateLimite() != null)
                existing.setDateLimite(req.getDateLimite());
            if (req.getCoursId() != null) {
                Cours c = coursRepository.findById(req.getCoursId())
                        .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
                existing.setCours(c);
            }
            return ResponseEntity.ok(devoirRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!devoirRepository.existsById(id))
            return ResponseEntity.notFound().build();
        devoirRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
