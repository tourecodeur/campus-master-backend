package com.campusmaster.presentation.controller;

import com.campusmaster.application.service.ServiceCours;
import com.campusmaster.application.service.ServiceDepotDevoir;
import com.campusmaster.application.service.ServiceDevoir;
import com.campusmaster.application.service.ServiceNoteDevoir;
import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Discussion;
import com.campusmaster.domaine.entite.SupportCours;
import com.campusmaster.infrastructure.repository.DiscussionRepository;
import com.campusmaster.infrastructure.repository.SupportCoursRepository;
import com.campusmaster.infrastructure.security.CurrentUserService;
// import com.campusmaster.domaine.repository.DiscussionRepository;
// import com.campusmaster.domaine.repository.SupportCoursRepository;
import com.campusmaster.presentation.dto.CoursDto;
import com.campusmaster.presentation.dto.NoteDevoirDto;
// import com.campusmaster.security.CurrentUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin
@Tag(name = "Espace Étudiant")
public class EspaceEtudiantController {

    private final ServiceCours serviceCours;
    private final ServiceDepotDevoir serviceDepotDevoir;
    private final ServiceNoteDevoir serviceNoteDevoir;
    private final CurrentUserService currentUserService;
    private final ServiceDevoir serviceDevoir;
    private final SupportCoursRepository supportCoursRepository;
    private final DiscussionRepository discussionRepository;

    public EspaceEtudiantController(ServiceCours serviceCours,
            ServiceDepotDevoir serviceDepotDevoir,
            ServiceNoteDevoir serviceNoteDevoir,
            CurrentUserService currentUserService,
            ServiceDevoir serviceDevoir,
            SupportCoursRepository supportCoursRepository,
            DiscussionRepository discussionRepository) {
        this.serviceCours = serviceCours;
        this.serviceDepotDevoir = serviceDepotDevoir;
        this.serviceNoteDevoir = serviceNoteDevoir;
        this.currentUserService = currentUserService;

        this.serviceDevoir = serviceDevoir;
        this.supportCoursRepository = supportCoursRepository;
        this.discussionRepository = discussionRepository;
    }

    /**
     * Version recommandée (frontend): utilise l'utilisateur connecté.
     */
    @GetMapping("/me/cours")
    public ResponseEntity<List<CoursDto>> mesCours() {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        List<CoursDto> dtos = serviceCours.listerCoursPourEtudiant(etudiantId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/me/devoirs/{devoirId}/depots")
    public ResponseEntity<DepotDevoir> deposerDevoir(@PathVariable Long devoirId,
            @RequestParam String urlFichier) {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        DepotDevoir depot = serviceDepotDevoir.deposerDevoir(devoirId, etudiantId, urlFichier);
        return ResponseEntity.ok(depot);
    }

    @GetMapping("/me/notes")
    public ResponseEntity<List<NoteDevoirDto>> mesNotes() {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        return ResponseEntity.ok(serviceNoteDevoir.listerNotesEtudiant(etudiantId));
    }

    @GetMapping("/me/devoirs")
    public ResponseEntity<List<Devoir>> mesDevoirs() {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        List<Cours> mesCours = serviceCours.listerCoursPourEtudiant(etudiantId);

        List<Devoir> result = new ArrayList<>();
        for (Cours c : mesCours) {
            result.addAll(serviceDevoir.listerDevoirsParCours(c.getId()));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/supports-cours")
    public ResponseEntity<List<SupportCours>> mesSupports() {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        List<Cours> mesCours = serviceCours.listerCoursPourEtudiant(etudiantId);

        List<SupportCours> result = new ArrayList<>();
        for (Cours c : mesCours) {
            result.addAll(supportCoursRepository.findByCours(c));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/discussions")
    public ResponseEntity<List<Discussion>> mesDiscussions() {
        Long etudiantId = currentUserService.getCurrentUtilisateur().getId();
        List<Cours> mesCours = serviceCours.listerCoursPourEtudiant(etudiantId);

        List<Discussion> result = new ArrayList<>();
        for (Cours c : mesCours) {
            result.addAll(discussionRepository.findByCours(c));
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Compatibilité : endpoints historiques avec etudiantId.
     * Sécurisé : ETUDIANT ne peut appeler que son propre ID. ADMIN peut tout.
     */
    @GetMapping("/{etudiantId}/cours")
    public ResponseEntity<List<CoursDto>> mesCoursCompat(@PathVariable Long etudiantId) {
        currentUserService.assertSelfOrAdmin(etudiantId);
        List<Cours> cours = serviceCours.listerCoursPourEtudiant(etudiantId);

        List<CoursDto> dtos = cours.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{etudiantId}/devoirs/{devoirId}/depots")
    public ResponseEntity<DepotDevoir> deposerDevoirCompat(@PathVariable Long etudiantId,
            @PathVariable Long devoirId,
            @RequestParam String urlFichier) {
        currentUserService.assertSelfOrAdmin(etudiantId);
        DepotDevoir depot = serviceDepotDevoir.deposerDevoir(devoirId, etudiantId, urlFichier);
        return ResponseEntity.ok(depot);
    }

    @GetMapping("/{etudiantId}/notes")
    public ResponseEntity<List<NoteDevoirDto>> listerNotesCompat(@PathVariable Long etudiantId) {
        currentUserService.assertSelfOrAdmin(etudiantId);
        return ResponseEntity.ok(serviceNoteDevoir.listerNotesEtudiant(etudiantId));
    }

    private CoursDto toDto(Cours c) {
        CoursDto dto = new CoursDto();
        dto.setId(c.getId());
        dto.setTitre(c.getTitre());
        dto.setDescription(c.getDescription());
        if (c.getMatiere() != null) {
            dto.setMatiere(c.getMatiere().getLibelle());
            if (c.getMatiere().getModule() != null) {
                dto.setModule(c.getMatiere().getModule().getLibelle());
            }
        }
        if (c.getSemestre() != null) {
            dto.setSemestre(c.getSemestre().getCode());
        }
        if (c.getEnseignant() != null) {
            dto.setEnseignant(c.getEnseignant().getNomComplet());
        }
        return dto;
    }
}
