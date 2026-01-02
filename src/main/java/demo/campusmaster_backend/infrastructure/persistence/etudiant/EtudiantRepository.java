package demo.campusmaster_backend.infrastructure.persistence.etudiant;

import demo.campusmaster_backend.domain.etudiant.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    boolean existsByMatricule(String matricule);
}
