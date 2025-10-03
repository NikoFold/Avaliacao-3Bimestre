package clinica.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import clinica.com.service.PacienteService;
import clinica.com.entity.Paciente;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "cadastrarPaciente";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Paciente paciente, Model model) {
        pacienteService.save(paciente);
        model.addAttribute("mensagemSucesso", "Paciente salvo com sucesso");
        return form(model);
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("pacientes", pacienteService.list());
        return "listarPaciente";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.findById(id));
        return "editarPaciente";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Paciente paciente, Model model) {
        pacienteService.save(paciente);
        model.addAttribute("mensagemSucesso", "Paciente atualizado com sucesso");
        model.addAttribute("pacientes", pacienteService.list());
        return "listarPaciente";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        pacienteService.deleteById(id);
        model.addAttribute("pacientes", pacienteService.list());
        model.addAttribute("mensagemSucesso", "Paciente removido com sucesso");
        return "listarPaciente";
    }
}