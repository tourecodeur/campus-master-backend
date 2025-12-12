package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.Cours;
import com.campusmaster.domaine.entite.Devoir;
import com.campusmaster.domaine.repository.CoursRepository;
import com.campusmaster.domaine.repository.DevoirRepository;
import com.campusmaster.domaine.service.ServiceDevoir;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDevoirImpl implements ServiceDevoir {

    private final DevoirRepository devoirRepository;
    private final CoursRepository coursRepository;

    public ServiceDevoirImpl(DevoirRepository devoirRepository,
                             CoursRepository coursRepository) {
        this.devoirRepository = devoirRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public Devoir creerDevoir(Devoir devoir, Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
        devoir.setCours(cours);
        return devoirRepository.save(devoir);
    }

    @Override
    public List<Devoir> listerDevoirsParCours(Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours introuvable"));
        return devoirRepository.findByCours(cours);
    }
}
