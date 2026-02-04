package com.campusmaster.presentation.controller;

import com.campusmaster.application.service.ServiceCours;
import com.campusmaster.application.service.ServiceDevoir;
import com.campusmaster.application.service.ServiceNoteDevoir;
import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
import com.campusmaster.infrastructure.security.CurrentUserService;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.NoteDevoirDto;
// import com.campusmaster.security.CurrentUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin
@Tag(name = "Espace Enseignant")
public class EspaceEnseignantController {

    private final ServiceCours serviceCours;
    private final ServiceDevoir serviceDevoir;
    private final ServiceNoteDevoir serviceNoteDevoir;
    private final CurrentUserService currentUserService;
    private final UtilisateurRepository utilisateurRepository;

    public EspaceEnseignantController(ServiceCours serviceCours,
            ServiceDevoir serviceDevoir,
            ServiceNoteDevoir serviceNoteDevoir,
            CurrentUserService currentUserService,
            UtilisateurRepository utilisateurRepository) {
        this.serviceCours = serviceCours;
        this.serviceDevoir = serviceDevoir;
        this.serviceNoteDevoir = serviceNoteDevoir;
        this.currentUserService = currentUserService;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Version recommandée (frontend): utilise l'utilisateur connecté.
     */
    @GetMapping("/me/cours")
    public ResponseEntity<List<Cours>> mesCours() {
        Long enseignantId = currentUserService.getCurrentUtilisateur().getId();
        return ResponseEntity.ok(serviceCours.listerCoursPourEnseignant(enseignantId));
    }

    @PostMapping("/me/cours")
    public ResponseEntity<Cours> creerCours(@RequestBody Cours cours) {
        Utilisateur enseignant = currentUserService.getCurrentUtilisateur();
        cours.setEnseignant(enseignant);
        return ResponseEntity.ok(serviceCours.creerCours(cours));
    }

    @PostMapping("/me/cours/{coursId}/devoirs")
    public ResponseEntity<Devoir> creerDevoir(@PathVariable Long coursId,
            @RequestBody Devoir devoir) {
        return ResponseEntity.ok(serviceDevoir.creerDevoir(devoir, coursId));
    }

    @PostMapping("/me/depots/{depotId}/noter")
    public ResponseEntity<NoteDevoirDto> noter(@PathVariable Long depotId,
            @RequestParam Double note,
            @RequestParam String commentaire) {
        return ResponseEntity.ok(serviceNoteDevoir.noterDepot(depotId, note, commentaire));
    }

    /**
     * Compatibilité : endpoints historiques avec enseignantId.
     * Sécurisé : ENSEIGNANT ne peut appeler que son propre ID. ADMIN peut tout.
     */
    @PostMapping("/{enseignantId}/cours")
    public ResponseEntity<Cours> creerCoursCompat(@PathVariable Long enseignantId,
            @RequestBody Cours cours) {
        currentUserService.assertSelfOrAdmin(enseignantId);
        Utilisateur enseignant = utilisateurRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant introuvable: " + enseignantId));
        cours.setEnseignant(enseignant);
        return ResponseEntity.ok(serviceCours.creerCours(cours));
    }

    @GetMapping("/{enseignantId}/cours")
    public ResponseEntity<List<Cours>> listerCours(@PathVariable Long enseignantId) {
        currentUserService.assertSelfOrAdmin(enseignantId);
        return ResponseEntity.ok(serviceCours.listerCoursPourEnseignant(enseignantId));
    }

    @PostMapping("/cours/{coursId}/devoirs")
    public ResponseEntity<Devoir> creerDevoirCompat(@PathVariable Long coursId,
            @RequestBody Devoir devoir) {
        return ResponseEntity.ok(serviceDevoir.creerDevoir(devoir, coursId));
    }

    @PostMapping("/depots/{depotId}/noter")
    public ResponseEntity<NoteDevoirDto> noterCompat(@PathVariable Long depotId,
            @RequestParam Double note,
            @RequestParam String commentaire) {
        return ResponseEntity.ok(serviceNoteDevoir.noterDepot(depotId, note, commentaire));
    }
}
