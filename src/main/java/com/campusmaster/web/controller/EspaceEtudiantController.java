package com.campusmaster.web.controller;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.service.*;
import com.campusmaster.web.dto.CoursDto;
import com.campusmaster.web.dto.NoteDevoirDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Espace étudiant : consultation des cours, dépôt de devoirs, notes, discussions.
 */
@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin
@Tag(name = "Espace Étudiant")
public class EspaceEtudiantController {

    private final ServiceCours serviceCours;
    private final ServiceDepotDevoir serviceDepotDevoir;
    private final ServiceNoteDevoir serviceNoteDevoir;

    public EspaceEtudiantController(ServiceCours serviceCours,
                                    ServiceDepotDevoir serviceDepotDevoir,
                                    ServiceNoteDevoir serviceNoteDevoir) {
        this.serviceCours = serviceCours;
        this.serviceDepotDevoir = serviceDepotDevoir;
        this.serviceNoteDevoir = serviceNoteDevoir;
    }

    @GetMapping("/{etudiantId}/cours")
    public ResponseEntity<List<CoursDto>> listerCours(@PathVariable Long etudiantId) {
        List<Cours> cours = serviceCours.listerCoursPourEtudiant(etudiantId);
        List<CoursDto> dtos = cours.stream().map(c -> {
            CoursDto dto = new CoursDto();
            dto.setId(c.getId());
            dto.setTitre(c.getTitre());
            dto.setDescription(c.getDescription());
            dto.setMatiere(c.getMatiere() != null ? c.getMatiere().getLibelle() : null);
            dto.setModule(c.getMatiere() != null && c.getMatiere().getModule() != null ? c.getMatiere().getModule().getLibelle() : null);
            dto.setSemestre(c.getSemestre() != null ? c.getSemestre().getCode() : null);
            dto.setEnseignant(c.getEnseignant() != null ? c.getEnseignant().getNomComplet() : null);
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{etudiantId}/devoirs/{devoirId}/depots")
    public ResponseEntity<DepotDevoir> deposerDevoir(@PathVariable Long etudiantId,
                                                     @PathVariable Long devoirId,
                                                     @RequestParam String urlFichier) {
        DepotDevoir depot = serviceDepotDevoir.deposerDevoir(devoirId, etudiantId, urlFichier);
        return ResponseEntity.ok(depot);
    }

    @GetMapping("/{etudiantId}/notes")
    public ResponseEntity<List<NoteDevoirDto>> listerNotes(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(serviceNoteDevoir.listerNotesEtudiant(etudiantId));
    }
}
