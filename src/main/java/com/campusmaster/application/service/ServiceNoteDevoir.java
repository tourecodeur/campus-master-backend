package com.campusmaster.application.service;

import java.util.List;

import com.campusmaster.presentation.dto.NoteDevoirDto;

public interface ServiceNoteDevoir {

    NoteDevoirDto noterDepot(Long depotId, Double note, String commentaire);

    List<NoteDevoirDto> listerNotesEtudiant(Long etudiantId);
}
