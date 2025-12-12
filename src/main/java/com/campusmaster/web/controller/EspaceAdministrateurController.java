package com.campusmaster.web.controller;

import com.campusmaster.domaine.entite.Module;
import com.campusmaster.domaine.entite.Matiere;
import com.campusmaster.domaine.entite.Semestre;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.repository.ModuleRepository;
import com.campusmaster.domaine.repository.MatiereRepository;
import com.campusmaster.domaine.repository.SemestreRepository;
import com.campusmaster.domaine.service.ServiceUtilisateur;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Espace administrateur : gestion des utilisateurs, modules, matières, semestres.
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@Tag(name = "Espace Administrateur")
public class EspaceAdministrateurController {

    private final ServiceUtilisateur serviceUtilisateur;
    private final ModuleRepository moduleRepository;
    private final MatiereRepository matiereRepository;
    private final SemestreRepository semestreRepository;

    public EspaceAdministrateurController(ServiceUtilisateur serviceUtilisateur,
                                          ModuleRepository moduleRepository,
                                          MatiereRepository matiereRepository,
                                          SemestreRepository semestreRepository) {
        this.serviceUtilisateur = serviceUtilisateur;
        this.moduleRepository = moduleRepository;
        this.matiereRepository = matiereRepository;
        this.semestreRepository = semestreRepository;
    }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> listerUtilisateurs() {
        return ResponseEntity.ok(serviceUtilisateur.listerUtilisateurs());
    }

    @PostMapping("/modules")
    public ResponseEntity<Module> creerModule(@RequestBody Module module) {
        return ResponseEntity.ok(moduleRepository.save(module));
    }

    @PostMapping("/matieres")
    public ResponseEntity<Matiere> creerMatiere(@RequestBody Matiere matiere) {
        return ResponseEntity.ok(matiereRepository.save(matiere));
    }

    @PostMapping("/semestres")
    public ResponseEntity<Semestre> creerSemestre(@RequestBody Semestre semestre) {
        return ResponseEntity.ok(semestreRepository.save(semestre));
    }
}
