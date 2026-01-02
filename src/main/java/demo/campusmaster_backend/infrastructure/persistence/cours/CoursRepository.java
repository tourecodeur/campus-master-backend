package demo.campusmaster_backend.infrastructure.persistence.cours;
import demo.campusmaster_backend.domain.cours.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
}

