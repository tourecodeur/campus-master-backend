package com.campusmaster.web.controller;

import com.campusmaster.domaine.service.ServiceTableauDeBord;
import com.campusmaster.web.dto.StatistiquesTableauDeBordDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Tableau de bord analytique : statistiques globales.
 */
@RestController
@RequestMapping("/api/tableau-de-bord")
@CrossOrigin
@Tag(name = "Tableau de bord analytique")
public class TableauDeBordController {

    private final ServiceTableauDeBord serviceTableauDeBord;

    public TableauDeBordController(ServiceTableauDeBord serviceTableauDeBord) {
        this.serviceTableauDeBord = serviceTableauDeBord;
    }

    @GetMapping("/statistiques")
    public ResponseEntity<StatistiquesTableauDeBordDto> statistiquesGlobales() {
        return ResponseEntity.ok(serviceTableauDeBord.genererStatistiquesGlobales());
    }
}
