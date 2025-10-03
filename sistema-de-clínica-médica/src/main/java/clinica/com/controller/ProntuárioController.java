package clinica.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import clinica.com.entity.Prontuário;
import clinica.com.service.ProntuárioService;

@Controller
@RequestMapping("/prontuario")
public class ProntuárioController {

    @Autowired
    private ProntuárioService prontuarioService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("prontuario", new Prontuário());
        return "cadastrarProntuario";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Prontuário prontuario, Model model) {
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
    public String update(@ModelAttribute Prontuário prontuario, Model model) {
        prontuarioService.save(prontuario);
        model.addAttribute("mensagemSucesso", "Prontuário atualizado com sucesso");
        model.addAttribute("prontuarios", prontuarioService.list());
        return "listarProntuario";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        prontuarioService.deleteById(id);
        model.addAttribute("prontuarios", prontuarioService.list());
        model.addAttribute("mensagemSucesso", "Prontuário removido com sucesso");
        return "listarProntuario";
    }
}