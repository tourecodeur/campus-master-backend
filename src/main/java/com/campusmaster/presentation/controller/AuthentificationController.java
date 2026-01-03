package com.campusmaster.presentation.controller;

import com.campusmaster.application.service.ServiceUtilisateur;
import com.campusmaster.domaine.entite.Utilisateur;
import com.campusmaster.infrastructure.security.JwtTokenProvider;
import com.campusmaster.presentation.dto.AuthentificationRequest;
import com.campusmaster.presentation.dto.AuthentificationResponse;
import com.campusmaster.presentation.dto.InscriptionRequest;
// import com.campusmaster.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Tag(name = "Authentification")
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ServiceUtilisateur serviceUtilisateur;

    public AuthentificationController(AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            ServiceUtilisateur serviceUtilisateur) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.serviceUtilisateur = serviceUtilisateur;
    }

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscription(@RequestBody InscriptionRequest request) {
        Utilisateur utilisateur = serviceUtilisateur.inscrireUtilisateur(request);
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping("/connexion")
    public ResponseEntity<AuthentificationResponse> connexion(@RequestBody AuthentificationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.genererToken(request.getEmail());
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return ResponseEntity.ok(new AuthentificationResponse(token, role));
    }
}
