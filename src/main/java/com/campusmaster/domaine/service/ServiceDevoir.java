package com.campusmaster.domaine.service;

import com.campusmaster.domaine.entite.Devoir;

import java.util.List;

public interface ServiceDevoir {

    Devoir creerDevoir(Devoir devoir, Long coursId);

    List<Devoir> listerDevoirsParCours(Long coursId);
}
