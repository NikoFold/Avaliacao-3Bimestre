package clinica.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import clinica.com.entity.Paciente;
import clinica.com.entity.Prontuario;
import clinica.com.service.PacienteService;
import clinica.com.service.ProntuarioService;

@Controller
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("prontuario", new Prontuario());
        model.addAttribute("pacientes", pacienteService.list());
        return "cadastrarProntuario";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Prontuario prontuario, @RequestParam("pacienteId") Long pacienteId, Model model) {
        Prontuario prontuarioExistente = prontuarioService.findByPacienteId(pacienteId);

        if (prontuarioExistente != null) {
            model.addAttribute("mensagemErro", "O paciente já possui um prontuário cadastrado.");
            model.addAttribute("prontuario", prontuario);
            model.addAttribute("pacientes", pacienteService.list());
            return "cadastrarProntuario";
        }

        Paciente paciente = pacienteService.findById(pacienteId);
        if (paciente == null) {
            model.addAttribute("mensagemErro", "Paciente selecionado é inválido ou não foi encontrado.");
            model.addAttribute("prontuario", prontuario);
            model.addAttribute("pacientes", pacienteService.list());
            return "cadastrarProntuario";
        }

        prontuario.setPaciente(paciente);
        prontuarioService.save(prontuario);
        model.addAttribute("mensagemSucesso", "Prontuário salvo com sucesso");
        return form(model);
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("prontuarios", prontuarioService.list());
        return "listarProntuario";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("prontuario", prontuarioService.findById(id));
        return "editarProntuario";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Prontuario prontuario, Model model) {
        prontuarioService.save(prontuario);
        model.addAttribute("mensagemSucesso", "Prontuário atualizado com sucesso");
        model.addAttribute("prontuarios", prontuarioService.list());
        return "listarProntuario";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
    	Prontuario prontuario = prontuarioService.findById(id);
        if (prontuario.getPaciente() != null) {
            prontuario.getPaciente().setProntuario(null);
        }
    	prontuarioService.deleteById(id);
        model.addAttribute("prontuarios", prontuarioService.list());
        model.addAttribute("mensagemSucesso", "Prontuário removido com sucesso");
        return "listarProntuario";
    }
}