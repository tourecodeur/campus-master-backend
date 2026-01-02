package demo.campusmaster_backend.application.cours;
import demo.campusmaster_backend.infrastructure.persistence.cours.CoursRepository;
import demo.campusmaster_backend.domain.cours.Cours;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CoursService {

    private final CoursRepository repository;

    public CoursService(CoursRepository repository) {
        this.repository = repository;
    }

    public List<Cours> findAll() {
        return repository.findAll();
    }

    public Cours save(Cours cours) {
        return repository.save(cours);
    }

    public Cours update(Long id, Cours cours) {
        Cours existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

        existing.setCode(cours.getCode());
        existing.setTitre(cours.getTitre());
        existing.setNiveau(cours.getNiveau());
        existing.setEnseignant_id(cours.getEnseignant_id());
        existing.setHeures(cours.getHeures());
        existing.setSemestre_id(cours.getSemestre_id());
        existing.setMatiere_id(cours.getMatiere_id());
        existing.setCredits(cours.getCredits());
        existing.setStatut(cours.getStatut());
        existing.setDescription(cours.getDescription());
        existing.setEtudiantsInscrits(cours.getEtudiantsInscrits());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
