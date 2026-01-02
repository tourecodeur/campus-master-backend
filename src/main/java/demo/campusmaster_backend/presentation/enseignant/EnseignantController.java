package demo.campusmaster_backend.presentation.enseignant;

import demo.campusmaster_backend.application.enseignant.EnseignantService;
import demo.campusmaster_backend.application.enseignant.EnseignantRequest;
import demo.campusmaster_backend.domain.enseignant.Enseignant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    // ===================== GET ALL =====================
    @GetMapping
    public List<Enseignant> getAll() {
        return enseignantService.getAll();
    }

    // ===================== GET BY ID =====================
    @GetMapping("/{id}")
    public Enseignant getById(@PathVariable Long id) {
        return enseignantService.getById(id);
    }

    // ===================== CREATE =====================
    @PostMapping
    public Enseignant create(@RequestBody EnseignantRequest request) {
        return enseignantService.create(request);
    }

    // ===================== UPDATE =====================
    @PutMapping("/{id}")
    public Enseignant update(@PathVariable Long id, @RequestBody EnseignantRequest request) {
        return enseignantService.update(id, request);
    }

    // ===================== DELETE =====================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enseignantService.delete(id);
    }
}
