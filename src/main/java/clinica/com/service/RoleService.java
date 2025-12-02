package clinica.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinica.com.entity.Role;
import clinica.com.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	protected RoleRepository roleRepository;
	
	public Role save(Role r) {
		return roleRepository.save(r);
	}
	
	public List<Role> list() {
		return roleRepository.findAll();
	}
	
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}
	
	public Role findById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}
	
	public Role findByNome(String nome) {
	    return roleRepository.findByNome(nome);
	}
}