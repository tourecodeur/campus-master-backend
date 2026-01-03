package com.campusmaster.infrastructure.security;

import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.domaine.enums.TypeRole;
import com.campusmaster.infrastructure.repository.UtilisateurRepository;

// import com.campusmaster.domaine.repository.UtilisateurRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService {

    private final UtilisateurRepository utilisateurRepository;

    public CurrentUserService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur getCurrentUtilisateur() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new AccessDeniedException("Non authentifié");
        }
        String email = auth.getName();
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new AccessDeniedException("Utilisateur connecté introuvable"));
    }

    public boolean isAdmin() {
        Utilisateur u = getCurrentUtilisateur();
        return u.getRole() == TypeRole.ADMIN;
    }

    public void assertSelfOrAdmin(Long utilisateurId) {
        Utilisateur u = getCurrentUtilisateur();
        if (u.getRole() == TypeRole.ADMIN)
            return;
        if (u.getId() == null || !u.getId().equals(utilisateurId)) {
            throw new AccessDeniedException("Accès interdit : ressource d'un autre utilisateur");
        }
    }

    public void assertRoleOrAdmin(TypeRole role) {
        Utilisateur u = getCurrentUtilisateur();
        if (u.getRole() == TypeRole.ADMIN)
            return;
        if (u.getRole() != role) {
            throw new AccessDeniedException("Accès interdit : rôle insuffisant");
        }
    }
}
