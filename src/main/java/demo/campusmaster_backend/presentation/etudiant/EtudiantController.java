package demo.campusmaster_backend.presentation.etudiant;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.campusmaster_backend.application.etudiant.EtudiantService;
import demo.campusmaster_backend.application.etudiant.EtudiantRequest;
import demo.campusmaster_backend.application.etudiant.EtudiantResponse;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping
    public ResponseEntity<List<EtudiantResponse>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantResponse> getEtudiant(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @PostMapping
    public ResponseEntity<EtudiantResponse> createEtudiant(@RequestBody EtudiantRequest request) {
        return ResponseEntity.ok(etudiantService.createEtudiant(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantResponse> updateEtudiant(
            @PathVariable Long id,
            @RequestBody EtudiantRequest request) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}
