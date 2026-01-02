package demo.campusmaster_backend.infrastructure.config;

import demo.campusmaster_backend.domain.auth.Role;
import demo.campusmaster_backend.domain.auth.User;
import demo.campusmaster_backend.infrastructure.persistence.auth.UserRepository;

import demo.campusmaster_backend.domain.etudiant.Etudiant;
import demo.campusmaster_backend.infrastructure.persistence.etudiant.EtudiantRepository;

import demo.campusmaster_backend.domain.enseignant.Enseignant;
import demo.campusmaster_backend.infrastructure.persistence.enseignant.EnseignantRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EtudiantRepository etudiantRepository;
    private final EnseignantRepository enseignantRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           EtudiantRepository etudiantRepository,
                           EnseignantRepository enseignantRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.etudiantRepository = etudiantRepository;
        this.enseignantRepository = enseignantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // ==== ADMIN ====
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User adminUser = new User(
                    "admin@example.com",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
            );
            userRepository.save(adminUser);
        }

        // ==== ENSEIGNANT ====
        if (userRepository.findByEmail("enseignant@example.com").isEmpty()) {
            // User pour auth
            User enseignantUser = new User(
                    "enseignant@example.com",
                    passwordEncoder.encode("enseignant123"),
                    Role.ENSEIGNANT
            );
            userRepository.save(enseignantUser);

            // Entité métier Enseignant
            Enseignant enseignant = new Enseignant();
            enseignant.setNom("Enseignant");
            enseignant.setPrenom("Test");
            enseignant.setEmail("enseignant@example.com");
            enseignant.setTelephone("000000000");
            enseignant.setTitre("Professeur");
            enseignant.setDateEmbauche(LocalDate.of(2020, 1, 1));
            enseignant.setMatierePrincipale("Mathématiques");
            enseignant.setStatut("ACTIF");
            enseignantRepository.save(enseignant);
        }

        // ==== ETUDIANT ====
        if (userRepository.findByEmail("etudiant@example.com").isEmpty()) {
            // User pour auth
            User etudiantUser = new User(
                    "etudiant@example.com",
                    passwordEncoder.encode("etudiant123"),
                    Role.ETUDIANT
            );
            userRepository.save(etudiantUser);

            // Entité métier Etudiant
            Etudiant etudiant = new Etudiant();
            etudiant.setNom("Etudiant");
            etudiant.setPrenom("Test");
            etudiant.setEmail("etudiant@example.com");
            etudiant.setTelephone("775181489");
            etudiant.setNiveau("L1");
            etudiant.setStatut("ACTIF");
            etudiantRepository.save(etudiant);
        }

        System.out.println("✅ Données de test initialisées avec Clean Architecture");
    }
}
