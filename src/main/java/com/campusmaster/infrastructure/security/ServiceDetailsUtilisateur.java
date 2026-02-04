package com.campusmaster.infrastructure.security;

import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;

// import com.campusmaster.domaine.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDetailsUtilisateur implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public ServiceDetailsUtilisateur(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable : " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.isActif(),
                true,
                true,
                true,
                List.of(authority));
    }
}
