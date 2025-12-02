package clinica.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import clinica.com.entity.Role;
import clinica.com.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	protected RoleService roleService;
	
	@GetMapping("/form")
	public String home(Model model) {
		model.addAttribute("role", new Role());
		return "cadastrarRole";
	}
	
	@PostMapping("/save")
	public String saveRole(@ModelAttribute Role role, Model model) {
		String nome = role.getNome().trim().toUpperCase();
		
		if (!nome.startsWith("ROLE_")) {
			nome = "ROLE_" + nome;
		}
		if (roleService.findByNome(nome) != null) {
	        model.addAttribute("mensagemErro", "A role " + nome + " já existe");
	        model.addAttribute("role", new Role());
	        return "cadastrarRole";
	    }
		
		role.setNome(nome);
		
		roleService.save(role);
			model.addAttribute("mensagemSucesso", "Role " + role.getNome() + " salvo com sucesso");
			return "cadastrarRole";
		}
	
	@GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id, Model model) {
        try {
            Role role = roleService.findById(id);
            roleService.deleteById(id);
            model.addAttribute("roles", roleService.list());
            model.addAttribute("mensagemSucesso", "Role " + role.getNome() + " removida com sucesso");
            return "listarRole";
        } catch (RuntimeException e) {
            model.addAttribute("mensagemErro", e.getMessage());
            model.addAttribute("roles", roleService.list());
            return "listarRole";
        }
    }
	
	@GetMapping("/list")
	public String listarRole(Model model) {
		model.addAttribute("roles", roleService.list());
		return "listarRole";
	}
	
	@GetMapping("/edit/{id}")
	public String editRole(@PathVariable Long id, Model model) {
		Role role = roleService.findById(id);
		model.addAttribute("role", role);
		return "editarRole";
	}
	
	@PostMapping("/update")
	public String updateRole(@ModelAttribute Role role, Model model) {
		String nome = role.getNome().trim().toUpperCase();
		
		if (!nome.startsWith("ROLE_")) {
			nome = "ROLE_" + nome;
		}
		
		role.setNome(nome);
		
		roleService.save(role);
		model.addAttribute("mensagemSucesso", "Role " + role.getNome() + " editado com sucesso");
		model.addAttribute("roles", roleService.list());
		return "listarRole";
	}	
}

