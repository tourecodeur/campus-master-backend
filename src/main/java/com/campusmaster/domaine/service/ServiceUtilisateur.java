package com.campusmaster.domaine.service;

import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.web.dto.InscriptionRequest;

import java.util.List;

public interface ServiceUtilisateur {

    Utilisateur inscrireUtilisateur(InscriptionRequest request);

    List<Utilisateur> listerUtilisateurs();

    void activerUtilisateur(Long id);

    void desactiverUtilisateur(Long id);
}
