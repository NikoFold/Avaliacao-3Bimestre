package clinica.com.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import clinica.com.entity.Consulta;
import clinica.com.service.ConsultaService;
import clinica.com.service.PacienteService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteService.list());
        return "cadastrarConsulta";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Consulta consulta,
            @RequestParam("pacienteId") Long pacienteId,
            Model model) {

        if (pacienteId != null) {
            consulta.setPaciente(pacienteService.findById(pacienteId));
        }

        try {
            consultaService.save(consulta);
            model.addAttribute("mensagemSucesso", "Consulta salva com sucesso");
        } catch (Exception e) {
            model.addAttribute("mensagemErro", e.getMessage());
        }

        model.addAttribute("pacientes", pacienteService.list());
        return "cadastrarConsulta";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("consultas", consultaService.list());
        return "listarConsulta";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("consulta", consultaService.findById(id));
        return "editarConsulta";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Consulta consulta, Model model) {

        try {
            consultaService.save(consulta);
            model.addAttribute("mensagemSucesso", "Consulta atualizada com sucesso");
        } catch (Exception e) {
            model.addAttribute("mensagemErro", e.getMessage());
        }

        model.addAttribute("consultas", consultaService.list());
        return "listarConsulta";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        consultaService.deleteById(id);
        model.addAttribute("consultas", consultaService.list());
        model.addAttribute("mensagemSucesso", "Consulta removida com sucesso");
        return "listarConsulta";
    }
}