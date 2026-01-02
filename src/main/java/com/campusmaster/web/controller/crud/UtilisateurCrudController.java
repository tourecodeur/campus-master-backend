package com.campusmaster.web.controller.crud;

import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@CrossOrigin
@Tag(name = "Utilisateurs")
public class UtilisateurCrudController {

    private final UtilisateurRepository repository;

    public UtilisateurCrudController(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> lister() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> obtenir(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<Utilisateur> activer(@PathVariable Long id) {
        return repository.findById(id).map(u -> {
            u.setActif(true);
            return ResponseEntity.ok(repository.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/desactiver")
    public ResponseEntity<Utilisateur> desactiver(@PathVariable Long id) {
        return repository.findById(id).map(u -> {
            u.setActif(false);
            return ResponseEntity.ok(repository.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
