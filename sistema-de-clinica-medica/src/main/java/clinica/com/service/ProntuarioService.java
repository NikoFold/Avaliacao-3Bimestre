package clinica.com.service;

import clinica.com.repository.ProntuarioRepository;
import clinica.com.entity.Prontuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository repo;

    public Prontuario save(Prontuario p) { 
    	return repo.save(p); }
    
    public List<Prontuario> list() { 
    	return repo.findAll(); }
    
    public Prontuario findByPacienteId(Long pacienteId) { 
    	return repo.findByPacienteId(pacienteId); }
    
    public Prontuario findById(Long id) {
    	return repo.findById(id).orElse(null); }
    
    public void deleteById(Long id) { 
    	repo.deleteById(id); }
}