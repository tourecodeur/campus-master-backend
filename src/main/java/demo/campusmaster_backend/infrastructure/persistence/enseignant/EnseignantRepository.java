package demo.campusmaster_backend.infrastructure.persistence.enseignant;

import demo.campusmaster_backend.domain.enseignant.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Optional<Enseignant> findByEmail(String email);
}
