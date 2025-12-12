package com.campusmaster.domaine.repository;

import com.campusmaster.domaine.entite.MessagePrive;
import com.campusmaster.domaine.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagePriveRepository extends JpaRepository<MessagePrive, Long> {

    List<MessagePrive> findByExpediteurAndDestinataireOrderByDateEnvoiAsc(Utilisateur expediteur, Utilisateur destinataire);
}
