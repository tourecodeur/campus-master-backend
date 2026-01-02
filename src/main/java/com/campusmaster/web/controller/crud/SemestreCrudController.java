package com.campusmaster.web.controller.crud;

import com.campusmaster.domaine.entite.Semestre;
import com.campusmaster.domaine.repository.SemestreRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/semestres")
@CrossOrigin
@Tag(name = "SemestreCrud")
public class SemestreCrudController {

    private final SemestreRepository repository;

    public SemestreCrudController(SemestreRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Semestre>> lister() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semestre> obtenir(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Semestre> creer(@RequestBody Semestre body) {
        body.setId(null);
        return ResponseEntity.ok(repository.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semestre> modifier(@PathVariable Long id, @RequestBody Semestre body) {
        return repository.findById(id)
                .map(existing -> {
                    body.setId(id);
                    return ResponseEntity.ok(repository.save(body));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
