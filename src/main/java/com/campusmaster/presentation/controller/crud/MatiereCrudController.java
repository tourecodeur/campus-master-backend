package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Matiere;
import com.campusmaster.domaine.entite.Module;
import com.campusmaster.infrastructure.repository.MatiereRepository;
import com.campusmaster.infrastructure.repository.ModuleRepository;
// import com.campusmaster.domaine.repository.MatiereRepository;
// import com.campusmaster.domaine.repository.ModuleRepository;
import com.campusmaster.presentation.dto.crud.MatiereRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matieres")
@CrossOrigin
@Tag(name = "Matieres")
public class MatiereCrudController {

    private final MatiereRepository matiereRepository;
    private final ModuleRepository moduleRepository;

    public MatiereCrudController(MatiereRepository matiereRepository, ModuleRepository moduleRepository) {
        this.matiereRepository = matiereRepository;
        this.moduleRepository = moduleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Matiere>> lister() {
        return ResponseEntity.ok(matiereRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> obtenir(@PathVariable Long id) {
        return matiereRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matiere> creer(@RequestBody MatiereRequest req) {
        Matiere m = new Matiere();
        m.setLibelle(req.getLibelle());
        if (req.getModuleId() != null) {
            Module module = moduleRepository.findById(req.getModuleId())
                    .orElseThrow(() -> new IllegalArgumentException("Module introuvable"));
            m.setModule(module);
        }
        return ResponseEntity.ok(matiereRepository.save(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> modifier(@PathVariable Long id, @RequestBody MatiereRequest req) {
        return matiereRepository.findById(id).map(existing -> {
            existing.setLibelle(req.getLibelle() != null ? req.getLibelle() : existing.getLibelle());
            if (req.getModuleId() != null) {
                Module module = moduleRepository.findById(req.getModuleId())
                        .orElseThrow(() -> new IllegalArgumentException("Module introuvable"));
                existing.setModule(module);
            }
            return ResponseEntity.ok(matiereRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!matiereRepository.existsById(id))
            return ResponseEntity.notFound().build();
        matiereRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
