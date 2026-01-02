package demo.campusmaster_backend.stats;
import org.springframework.web.bind.annotation.CrossOrigin;
import demo.campusmaster_backend.infrastructure.persistence.etudiant.EtudiantRepository;
import demo.campusmaster_backend.infrastructure.persistence.enseignant.EnseignantRepository;
import demo.campusmaster_backend.infrastructure.persistence.cours.CoursRepository;
//import demo.campusmaster_backend.devoir.repository.DevoirRepository;
//import demo.campusmaster_backend.document.repository.DocumentRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "http://localhost:3000")
public class StatsController {

    private final EtudiantRepository etudiantRepo;
    private final EnseignantRepository enseignantRepo;
    private final CoursRepository coursRepo;
   // private final DevoirRepository devoirRepo;
    //private final DocumentRepository documentRepo;

    public StatsController(EtudiantRepository etudiantRepo,
                           EnseignantRepository enseignantRepo,
                           CoursRepository coursRepo
                             ) {
                           //DevoirRepository devoirRepo,
                           //DocumentRepository documentRepo
                         
        this.etudiantRepo = etudiantRepo;
        this.enseignantRepo = enseignantRepo;
        this.coursRepo = coursRepo;
       // this.devoirRepo = devoirRepo;
        //this.documentRepo = documentRepo;
    }

    @GetMapping
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("etudiants", etudiantRepo.count());
        stats.put("enseignants", enseignantRepo.count());
        stats.put("cours", coursRepo.count());
        //stats.put("devoirs", devoirRepo.count());
        //stats.put("documents", documentRepo.count());
        return stats;
    }
}
