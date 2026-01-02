package demo.campusmaster_backend.application.etudiant;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.campusmaster_backend.domain.etudiant.Etudiant;
import demo.campusmaster_backend.infrastructure.persistence.etudiant.EtudiantRepository;

@Service
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public EtudiantResponse createEtudiant(EtudiantRequest request) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(request.getNom());
        etudiant.setPrenom(request.getPrenom());
        etudiant.setMatricule(request.getMatricule());
        etudiant.setEmail(request.getEmail());
        etudiant.setTelephone(request.getTelephone());
        etudiant.setNiveau(request.getNiveau());

           etudiant.setStatut(request.getStatut());

        Etudiant saved = etudiantRepository.save(etudiant);
        return mapToResponse(saved);
    }

    public List<EtudiantResponse> getAllEtudiants() {
        return etudiantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EtudiantResponse getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return mapToResponse(etudiant);
    }

    public EtudiantResponse updateEtudiant(Long id, EtudiantRequest request) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        etudiant.setNom(request.getNom());
        etudiant.setPrenom(request.getPrenom());
        etudiant.setMatricule(request.getMatricule());
        etudiant.setEmail(request.getEmail());
        etudiant.setTelephone(request.getTelephone());
        etudiant.setNiveau(request.getNiveau());
        etudiant.setStatut(request.getStatut());

        Etudiant updated = etudiantRepository.save(etudiant);
        return mapToResponse(updated);
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    private EtudiantResponse mapToResponse(Etudiant etudiant) {
        return new EtudiantResponse(
                etudiant.getId(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getMatricule(),
                etudiant.getEmail(),
                etudiant.getTelephone(),
                etudiant.getNiveau(),
            
              
                etudiant.getStatut()
        );
    }
}
