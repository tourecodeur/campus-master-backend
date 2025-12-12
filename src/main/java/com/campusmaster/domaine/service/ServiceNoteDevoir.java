package com.campusmaster.domaine.service;

import com.campusmaster.web.dto.NoteDevoirDto;

import java.util.List;

public interface ServiceNoteDevoir {

    NoteDevoirDto noterDepot(Long depotId, Double note, String commentaire);

    List<NoteDevoirDto> listerNotesEtudiant(Long etudiantId);
}
