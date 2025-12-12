package com.campusmaster.domaine.service.impl;

import com.campusmaster.domaine.entite.DepotDevoir;
import com.campusmaster.domaine.entite.NoteDevoir;
import com.campusmaster.domaine.repository.DepotDevoirRepository;
import com.campusmaster.domaine.repository.NoteDevoirRepository;
import com.campusmaster.domaine.service.ServiceNoteDevoir;
import com.campusmaster.web.dto.NoteDevoirDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceNoteDevoirImpl implements ServiceNoteDevoir {

    private final NoteDevoirRepository noteDevoirRepository;
    private final DepotDevoirRepository depotDevoirRepository;

    public ServiceNoteDevoirImpl(NoteDevoirRepository noteDevoirRepository,
                                 DepotDevoirRepository depotDevoirRepository) {
        this.noteDevoirRepository = noteDevoirRepository;
        this.depotDevoirRepository = depotDevoirRepository;
    }

    @Override
    public NoteDevoirDto noterDepot(Long depotId, Double note, String commentaire) {
        DepotDevoir depot = depotDevoirRepository.findById(depotId)
                .orElseThrow(() -> new IllegalArgumentException("Dépôt introuvable"));
        NoteDevoir noteDevoir = NoteDevoir.builder()
                .depot(depot)
                .note(note)
                .commentaire(commentaire)
                .build();
        noteDevoir = noteDevoirRepository.save(noteDevoir);

        NoteDevoirDto dto = new NoteDevoirDto();
        dto.setDepotId(depot.getId());
        dto.setNote(noteDevoir.getNote());
        dto.setCommentaire(noteDevoir.getCommentaire());
        dto.setDevoirTitre(depot.getDevoir().getTitre());
        return dto;
    }

    @Override
    public List<NoteDevoirDto> listerNotesEtudiant(Long etudiantId) {
        return noteDevoirRepository.findAll().stream()
                .filter(n -> n.getDepot().getEtudiant().getId().equals(etudiantId))
                .map(n -> {
                    NoteDevoirDto dto = new NoteDevoirDto();
                    dto.setDepotId(n.getDepot().getId());
                    dto.setNote(n.getNote());
                    dto.setCommentaire(n.getCommentaire());
                    dto.setDevoirTitre(n.getDepot().getDevoir().getTitre());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
