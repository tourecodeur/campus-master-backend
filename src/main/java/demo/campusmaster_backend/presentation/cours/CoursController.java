package demo.campusmaster_backend.presentation.cours;
import demo.campusmaster_backend.application.cours.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import demo.campusmaster_backend.domain.cours.Cours;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
@RestController
@RequestMapping("/api/cours")
@CrossOrigin(origins = "http://localhost:3000")
public class CoursController {

    private final CoursService service;

    public CoursController(CoursService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cours> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Cours create(@RequestBody Cours cours) {
        return service.save(cours);
    }

    @PutMapping("/{id}")
    public Cours update(@PathVariable Long id, @RequestBody Cours cours) {
        return service.update(id, cours);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
