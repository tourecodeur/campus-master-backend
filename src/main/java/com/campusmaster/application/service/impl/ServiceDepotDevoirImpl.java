package com.campusmaster.application.service.impl;

import com.campusmaster.application.service.ServiceDepotDevoir;
import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.entite.Utilisateur;
// import com.campusmaster.domaine.repository.DepotDevoirRepository;
// import com.campusmaster.domaine.repository.DevoirRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.infrastructure.repository.DepotDevoirRepository;
import com.campusmaster.infrastructure.repository.DevoirRepository;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceDepotDevoirImpl implements ServiceDepotDevoir {

        private final DepotDevoirRepository depotDevoirRepository;
        private final DevoirRepository devoirRepository;
        private final UtilisateurRepository utilisateurRepository;

        public ServiceDepotDevoirImpl(DepotDevoirRepository depotDevoirRepository,
                        DevoirRepository devoirRepository,
                        UtilisateurRepository utilisateurRepository) {
                this.depotDevoirRepository = depotDevoirRepository;
                this.devoirRepository = devoirRepository;
                this.utilisateurRepository = utilisateurRepository;
        }

        @Override
        public DepotDevoir deposerDevoir(Long devoirId, Long etudiantId, String urlFichier) {
                Devoir devoir = devoirRepository.findById(devoirId)
                                .orElseThrow(() -> new IllegalArgumentException("Devoir introuvable"));
                Utilisateur etudiant = utilisateurRepository.findById(etudiantId)
                                .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable"));

                List<DepotDevoir> depotsExistants = depotDevoirRepository
                                .findByDevoirAndEtudiantOrderByVersionDesc(devoir, etudiant);
                int nouvelleVersion = depotsExistants.isEmpty() ? 1 : depotsExistants.get(0).getVersion() + 1;

                DepotDevoir depot = DepotDevoir.builder()
                                .devoir(devoir)
                                .etudiant(etudiant)
                                .urlFichier(urlFichier)
                                .version(nouvelleVersion)
                                .dateDepot(LocalDateTime.now())
                                .build();
                return depotDevoirRepository.save(depot);
        }

        @Override
        public List<DepotDevoir> listerDepotsEtudiant(Long devoirId, Long etudiantId) {
                Devoir devoir = devoirRepository.findById(devoirId)
                                .orElseThrow(() -> new IllegalArgumentException("Devoir introuvable"));
                Utilisateur etudiant = utilisateurRepository.findById(etudiantId)
                                .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable"));

                return depotDevoirRepository.findByDevoirAndEtudiantOrderByVersionDesc(devoir, etudiant);
        }
}
