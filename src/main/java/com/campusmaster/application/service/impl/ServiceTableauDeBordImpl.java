package com.campusmaster.application.service.impl;

import com.campusmaster.application.service.ServiceTableauDeBord;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.infrastructure.repository.DepotDevoirRepository;
import com.campusmaster.infrastructure.repository.DevoirRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.DepotDevoirRepository;
// import com.campusmaster.domaine.repository.DevoirRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.DepotDevoirRepository;
// import com.campusmaster.domaine.repository.DevoirRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.StatistiquesTableauDeBordDto;

import org.springframework.stereotype.Service;

@Service
public class ServiceTableauDeBordImpl implements ServiceTableauDeBord {

    private final UtilisateurRepository utilisateurRepository;
    private final DevoirRepository devoirRepository;
    private final DepotDevoirRepository depotDevoirRepository;

    public ServiceTableauDeBordImpl(UtilisateurRepository utilisateurRepository,
            DevoirRepository devoirRepository,
            DepotDevoirRepository depotDevoirRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.devoirRepository = devoirRepository;
        this.depotDevoirRepository = depotDevoirRepository;
    }

    @Override
    public StatistiquesTableauDeBordDto genererStatistiquesGlobales() {
        long nbEtudiantsActifs = utilisateurRepository.findAll().stream()
                .filter(u -> u.isActif())
                .count();

        long nbDevoirs = devoirRepository.count();
        long nbDepots = 0;
        for (Devoir d : devoirRepository.findAll()) {
            nbDepots += depotDevoirRepository.countByDevoir(d);
        }
        double tauxRemise = nbDevoirs == 0 ? 0.0 : (double) nbDepots / (double) nbDevoirs;

        double performanceGlobale = 0.0;

        return StatistiquesTableauDeBordDto.builder()
                .nombreEtudiantsActifs(nbEtudiantsActifs)
                .tauxRemiseDevoirs(tauxRemise)
                .performanceGlobale(performanceGlobale)
                .build();
    }
}
