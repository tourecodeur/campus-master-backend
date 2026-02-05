package com.campusmaster.presentation.controller.crud;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Matiere;
import com.campusmaster.domaine.entite.Semestre;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.CoursRepository;
import com.campusmaster.infrastructure.repository.MatiereRepository;
import com.campusmaster.infrastructure.repository.SemestreRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.CoursRepository;
// import com.campusmaster.domaine.repository.MatiereRepository;
// import com.campusmaster.domaine.repository.SemestreRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.crud.CoursRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cours")
@CrossOrigin
@Tag(name = "Cours")
public class CoursCrudController {

    private final CoursRepository coursRepository;
    private final SemestreRepository semestreRepository;
    private final MatiereRepository matiereRepository;
    private final UtilisateurRepository utilisateurRepository;

    public CoursCrudController(CoursRepository coursRepository,
            SemestreRepository semestreRepository,
            MatiereRepository matiereRepository,
            UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.semestreRepository = semestreRepository;
        this.matiereRepository = matiereRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cours>> lister() {
        return ResponseEntity.ok(coursRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> obtenir(@PathVariable Long id) {
        return coursRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cours> creer(@RequestBody CoursRequest req) {
        Cours c = new Cours();
        c.setTitre(req.getTitre());
        c.setDescription(req.getDescription());
        if (req.getSemestreId() != null) {
            Semestre s = semestreRepository.findById(req.getSemestreId())
                    .orElseThrow(() -> new IllegalArgumentException("Semestre introuvable"));
            c.setSemestre(s);
        }
        if (req.getMatiereId() != null) {
            Matiere m = matiereRepository.findById(req.getMatiereId())
                    .orElseThrow(() -> new IllegalArgumentException("Matière introuvable"));
            c.setMatiere(m);
        }
        if (req.getEnseignantId() != null) {
            Utilisateur u = utilisateurRepository.findById(req.getEnseignantId())
                    .orElseThrow(() -> new IllegalArgumentException("Enseignant introuvable"));
            c.setEnseignant(u);
        }
        return ResponseEntity.ok(coursRepository.save(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> modifier(@PathVariable Long id, @RequestBody CoursRequest req) {
        return coursRepository.findById(id).map(existing -> {
            if (req.getTitre() != null)
                existing.setTitre(req.getTitre());
            if (req.getDescription() != null)
                existing.setDescription(req.getDescription());
            if (req.getSemestreId() != null) {
                Semestre s = semestreRepository.findById(req.getSemestreId())
                        .orElseThrow(() -> new IllegalArgumentException("Semestre introuvable"));
                existing.setSemestre(s);
            }
            if (req.getMatiereId() != null) {
                Matiere m = matiereRepository.findById(req.getMatiereId())
                        .orElseThrow(() -> new IllegalArgumentException("Matière introuvable"));
                existing.setMatiere(m);
            }
            if (req.getEnseignantId() != null) {
                Utilisateur u = utilisateurRepository.findById(req.getEnseignantId())
                        .orElseThrow(() -> new IllegalArgumentException("Enseignant introuvable"));
                existing.setEnseignant(u);
            }
            return ResponseEntity.ok(coursRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!coursRepository.existsById(id))
            return ResponseEntity.notFound().build();
        coursRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
