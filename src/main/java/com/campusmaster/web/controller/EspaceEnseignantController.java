package com.campusmaster.web.controller;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.service.ServiceCours;
import com.campusmaster.domaine.service.ServiceDevoir;
import com.campusmaster.domaine.service.ServiceNoteDevoir;
import com.campusmaster.web.dto.NoteDevoirDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Espace enseignant : gestion des cours, devoirs et correction.
 */
@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin
@Tag(name = "Espace Enseignant")
public class EspaceEnseignantController {

    private final ServiceCours serviceCours;
    private final ServiceDevoir serviceDevoir;
    private final ServiceNoteDevoir serviceNoteDevoir;

    public EspaceEnseignantController(ServiceCours serviceCours,
                                      ServiceDevoir serviceDevoir,
                                      ServiceNoteDevoir serviceNoteDevoir) {
        this.serviceCours = serviceCours;
        this.serviceDevoir = serviceDevoir;
        this.serviceNoteDevoir = serviceNoteDevoir;
    }

    @PostMapping("/cours")
    public ResponseEntity<Cours> creerCours(@RequestBody Cours cours) {
        return ResponseEntity.ok(serviceCours.creerCours(cours));
    }

    @GetMapping("/{enseignantId}/cours")
    public ResponseEntity<List<Cours>> listerCours(@PathVariable Long enseignentId) {
        return ResponseEntity.ok(serviceCours.listerCoursPourEnseignant(enseignentId));
    }

    @PostMapping("/cours/{coursId}/devoirs")
    public ResponseEntity<Devoir> creerDevoir(@PathVariable Long coursId,
                                              @RequestBody Devoir devoir) {
        return ResponseEntity.ok(serviceDevoir.creerDevoir(devoir, coursId));
    }

    @PostMapping("/depots/{depotId}/noter")
    public ResponseEntity<NoteDevoirDto> noter(@PathVariable Long depotId,
                                               @RequestParam Double note,
                                               @RequestParam String commentaire) {
        return ResponseEntity.ok(serviceNoteDevoir.noterDepot(depotId, note, commentaire));
    }
}
