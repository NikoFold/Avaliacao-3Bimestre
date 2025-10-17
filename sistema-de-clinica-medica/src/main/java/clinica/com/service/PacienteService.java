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
    
    public Paciente save(Paciente p) throws Exception { 
    	Paciente existenteEmail = repo.findByEmail(p.getEmail());
    	if (existenteEmail != null && !existenteEmail.getId().equals(p.getId())) {
			throw new Exception("Já existe um paciente com este e-mail.");
		}
    	
    	Paciente existenteTelefone = repo.findByTelefone(p.getTelefone());
    	if (existenteTelefone != null && !existenteTelefone.getId().equals(p.getId())) {
    					throw new Exception("Já existe um paciente com este telefone.");
    	}
    	
    	return repo.save(p); }
    
    public List<Paciente> list() { 
    	return repo.findAll(); }
    public Paciente findById(Long id) { 
    	return repo.findById(id).orElse(null); }
    public void deleteById(Long id) { repo.deleteById(id); }
}