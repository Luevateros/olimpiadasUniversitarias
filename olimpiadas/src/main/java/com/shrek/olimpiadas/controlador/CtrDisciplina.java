package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.DisciplinaDTO;
import com.shrek.olimpiadas.servicio.SvcDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CtrDisciplina {
    @Autowired
    private SvcDisciplina svc;

    @GetMapping("/disciplinas")
    public String mostrarFormNueva(Model model){
        model.addAttribute("disciplinas", svc.mostrarDisciplinas());
        return "ver_disciplinas";
    }

    @RequestMapping("/disciplina/nueva")
    public String mostrarFormaNueva(Model model) {
        model.addAttribute("tituloPagina", "Registrar disciplina");
        model.addAttribute("disciplina", new DisciplinaDTO());
        return "registro_disciplina";
    }

    @PostMapping("/disciplinas/guardar")
    public String agregarDisciplina(DisciplinaDTO disciplina, RedirectAttributes ra, Model model) {
        model.addAttribute("mensaje", "Registro completo.");
        String respuesta = svc.agregarDisciplina(disciplina);
        if (respuesta == null){
            ra.addFlashAttribute("mensaje", "La disciplina se agregó con éxito.");
            return "redirect:/disciplinas";
        }
        ra.addFlashAttribute("disciplina", new DisciplinaDTO());
        ra.addFlashAttribute("tituloPagina", "Registrar disciplina");
        ra.addFlashAttribute("mensaje", respuesta);
        return "redirect:/registro_disciplina";
    }

    @GetMapping("/disciplina/editar/{id}")
    public String actualizarDisciplina(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
        DisciplinaDTO disciplina = svc.mostrarDisciplina(id);
        if(disciplina == null) {
            ra.addFlashAttribute("mensaje", "No se encontró la disciplina.");
            return "redirect:/disciplinas";
        }
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("tituloPagina", "Editar disciplina");
        return "registro_disciplina";
    }

    @PostMapping("/disciplinas/actualizar/{id}")
    public void actDisciplina(DisciplinaDTO disciplina, RedirectAttributes ra, Model model){
        System.out.print(disciplina);
    }


    @GetMapping("/disciplinas/eliminar/{id}")
    public String eliminarDisciplina(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
        DisciplinaDTO disciplina = svc.mostrarDisciplina(id);
        if(disciplina== null) {
            ra.addFlashAttribute("mensaje", "No se encontró la disciplina");
            return "redirect:/disciplinas";
        }
        svc.eliminarDisciplina(id);
        ra.addFlashAttribute("mensaje", "La discilina se eliminó con éxito.");
        return "redirect:/disciplinas";
    }

}
