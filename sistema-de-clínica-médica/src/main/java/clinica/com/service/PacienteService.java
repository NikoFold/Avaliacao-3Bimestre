package clinica.com.service;

import clinica.com.repository.PacienteRepository;
import clinica.com.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {
	
    @Autowired
    private PacienteRepository repo;

    public Paciente save(Paciente p) { 
    	return repo.save(p); }
    public List<Paciente> list() { 
    	return repo.findAll(); }
    public Paciente findById(Long id) { 
    	return repo.findById(id).orElseThrow(); }
    public void deleteById(Long id) { repo.deleteById(id); }
}