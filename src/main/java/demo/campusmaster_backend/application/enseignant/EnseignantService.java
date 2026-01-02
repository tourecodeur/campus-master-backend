package demo.campusmaster_backend.application.enseignant;

import demo.campusmaster_backend.domain.enseignant.Enseignant;
import demo.campusmaster_backend.domain.auth.User;
import demo.campusmaster_backend.domain.auth.Role;
import demo.campusmaster_backend.infrastructure.persistence.enseignant.EnseignantRepository;
import demo.campusmaster_backend.infrastructure.persistence.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EnseignantService(EnseignantRepository enseignantRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.enseignantRepository = enseignantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Enseignant> getAll() {
        return enseignantRepository.findAll();
    }

    public Enseignant getById(Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
    }

    public Enseignant create(EnseignantRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ENSEIGNANT);
        userRepository.save(user);

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(request.getNom());
        enseignant.setPrenom(request.getPrenom());
        enseignant.setEmail(request.getEmail());
        enseignant.setTitre(request.getTitre());
        enseignant.setDateEmbauche(request.getDateEmbauche());
        enseignant.setMatierePrincipale(request.getMatierePrincipale());
        enseignant.setStatut(request.getStatut());
        enseignant.setTelephone(request.getTelephone());
        enseignant.setUser(user);

        return enseignantRepository.save(enseignant);
    }

    public Enseignant update(Long id, EnseignantRequest request) {
        Enseignant enseignant = getById(id);
        enseignant.setNom(request.getNom());
        enseignant.setPrenom(request.getPrenom());
        enseignant.setEmail(request.getEmail());
        enseignant.setTitre(request.getTitre());
        enseignant.setDateEmbauche(request.getDateEmbauche());
        enseignant.setMatierePrincipale(request.getMatierePrincipale());
        enseignant.setStatut(request.getStatut());
        enseignant.setTelephone(request.getTelephone());
        return enseignantRepository.save(enseignant);
    }

    public void delete(Long id) {
        enseignantRepository.deleteById(id);
    }
}
