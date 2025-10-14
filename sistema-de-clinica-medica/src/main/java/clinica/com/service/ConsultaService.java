package clinica.com.service;

import clinica.com.repository.ConsultaRepository;
import clinica.com.entity.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repo;

    public Consulta save(Consulta c) { return repo.save(c); }
    public List<Consulta> list() { return repo.findAll(); }
    public Consulta findById(Long id) { return repo.findById(id).orElseThrow(); }
    public void deleteById(Long id) { repo.deleteById(id); }
}