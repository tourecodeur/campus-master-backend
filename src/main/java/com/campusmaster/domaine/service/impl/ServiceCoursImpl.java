package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.repository.CoursRepository;
import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.domaine.service.ServiceCours;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCoursImpl implements ServiceCours {

    private final CoursRepository coursRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ServiceCoursImpl(CoursRepository coursRepository,
                            UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Cours creerCours(Cours cours) {
        return coursRepository.save(cours);
    }

    @Override
    public List<Cours> listerCoursPourEtudiant(Long etudiantId) {
        // Pour un vrai projet, lier les inscriptions aux cours.
        // Ici on renvoie tous les cours pour simplifier.
        return coursRepository.findAll();
    }

    @Override
    public List<Cours> listerCoursPourEnseignant(Long enseignantId) {
        Utilisateur enseignant = utilisateurRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant introuvable"));
        return coursRepository.findByEnseignant(enseignant);
    }

    @Override
    public Cours consulterCours(Long id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
    }

    @Override
    public void supprimerCours(Long id) {
        coursRepository.deleteById(id);
    }
}
