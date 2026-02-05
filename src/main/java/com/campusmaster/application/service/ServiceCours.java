package com.campusmaster.application.service;

import com.campusmaster.domaine.entite.Cours;

import java.util.List;

public interface ServiceCours {

    Cours creerCours(Cours cours);

    List<Cours> listerCoursPourEtudiant(Long etudiantId);

    List<Cours> listerCoursPourEnseignant(Long enseignantId);

    Cours consulterCours(Long id);

    void supprimerCours(Long id);
}
