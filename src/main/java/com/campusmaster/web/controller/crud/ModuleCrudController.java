package com.campusmaster.web.controller.crud;

import com.campusmaster.domaine.entite.Module;
import com.campusmaster.domaine.repository.ModuleRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
@CrossOrigin
@Tag(name = "ModuleCrud")
public class ModuleCrudController {

    private final ModuleRepository repository;

    public ModuleCrudController(ModuleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Module>> lister() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> obtenir(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Module> creer(@RequestBody Module body) {
        body.setId(null);
        return ResponseEntity.ok(repository.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> modifier(@PathVariable Long id, @RequestBody Module body) {
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
