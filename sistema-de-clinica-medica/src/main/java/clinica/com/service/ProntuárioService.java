package clinica.com.service;

import clinica.com.repository.ProntuárioRepository;
import clinica.com.entity.Prontuário;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProntuárioService {
    @Autowired
    private ProntuárioRepository repo;

    public Prontuário save(Prontuário p) { return repo.save(p); }
    public List<Prontuário> list() { return repo.findAll(); }
    public Prontuário findById(Long id) { return repo.findById(id).orElseThrow(); }
    public void deleteById(Long id) { repo.deleteById(id); }
}