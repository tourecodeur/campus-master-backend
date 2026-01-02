package com.campusmaster.web.controller.crud;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Discussion;
import com.campusmaster.domaine.repository.CoursRepository;
import com.campusmaster.domaine.repository.DiscussionRepository;
import com.campusmaster.web.dto.crud.DiscussionRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discussions")
@CrossOrigin
@Tag(name = "Discussions")
public class DiscussionCrudController {

    private final DiscussionRepository discussionRepository;
    private final CoursRepository coursRepository;

    public DiscussionCrudController(DiscussionRepository discussionRepository, CoursRepository coursRepository) {
        this.discussionRepository = discussionRepository;
        this.coursRepository = coursRepository;
    }

    @GetMapping
    public ResponseEntity<List<Discussion>> lister() {
        return ResponseEntity.ok(discussionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discussion> obtenir(@PathVariable Long id) {
        return discussionRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Discussion> creer(@RequestBody DiscussionRequest req) {
        Discussion d = new Discussion();
        d.setSujet(req.getSujet());
        if (req.getCoursId() != null) {
            Cours c = coursRepository.findById(req.getCoursId())
                    .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
            d.setCours(c);
        }
        return ResponseEntity.ok(discussionRepository.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discussion> modifier(@PathVariable Long id, @RequestBody DiscussionRequest req) {
        return discussionRepository.findById(id).map(existing -> {
            if (req.getSujet() != null) existing.setSujet(req.getSujet());
            if (req.getCoursId() != null) {
                Cours c = coursRepository.findById(req.getCoursId())
                        .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
                existing.setCours(c);
            }
            return ResponseEntity.ok(discussionRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!discussionRepository.existsById(id)) return ResponseEntity.notFound().build();
        discussionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
