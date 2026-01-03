package com.campusmaster.application.service;

import com.campusmaster.domaine.entite.DepotDevoir;

import java.util.List;

public interface ServiceDepotDevoir {

    DepotDevoir deposerDevoir(Long devoirId, Long etudiantId, String urlFichier);

    List<DepotDevoir> listerDepotsEtudiant(Long devoirId, Long etudiantId);
}
