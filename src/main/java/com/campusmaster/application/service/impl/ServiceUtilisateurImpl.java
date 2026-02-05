package com.campusmaster.application.service.impl;

import com.campusmaster.application.service.ServiceUtilisateur;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;
// import com.campusmaster.domaine.repository.UtilisateurRepository;
import com.campusmaster.presentation.dto.InscriptionRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUtilisateurImpl implements ServiceUtilisateur {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public ServiceUtilisateurImpl(UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur inscrireUtilisateur(InscriptionRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        Utilisateur utilisateur = Utilisateur.builder()
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .nomComplet(request.getNomComplet())
                .role(request.getRole())
                .actif(true)
                .build();
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void activerUtilisateur(Long id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        u.setActif(true);
        utilisateurRepository.save(u);
    }

    @Override
    public void desactiverUtilisateur(Long id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        u.setActif(false);
        utilisateurRepository.save(u);
    }
}
