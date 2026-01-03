package com.campusmaster.presentation.controller;

import com.campusmaster.application.service.ServiceTableauDeBord;
import com.campusmaster.presentation.dto.StatistiquesTableauDeBordDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
