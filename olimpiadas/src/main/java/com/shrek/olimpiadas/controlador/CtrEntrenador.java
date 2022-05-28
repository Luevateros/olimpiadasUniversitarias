package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.CompetidorDTO;
import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.dto.UsuarioDTO;
import com.shrek.olimpiadas.servicio.SvcEntrenador;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class CtrEntrenador {
    @Autowired
    private SvcEntrenador svc;

    @GetMapping("/registro_entrenador")
    public String registroEntrenador(Model model, Principal principal) {
        model.addAttribute("entrenador", new EntrenadorDTO());
        model.addAttribute("disciplinas", svc.mostrarDisciplina());
        model.addAttribute("tituloPagina", "Registrarse como entrenador");
        if (principal != null) {
            return "index";
        }
        return "registro_entrenador";
    }

    @PostMapping("/registro_entrenador/guardar")
    public String guardarCompetidor(EntrenadorDTO entrenador, RedirectAttributes ra, Model model) {
        model.addAttribute("mensaje", "Registro completo con exito");
        String respuesta = svc.agregarEntrenador(entrenador);
        if(respuesta == null) {
            ra.addFlashAttribute("mensaje", "El entrenador nuevo se agregó con exitó.");

            ra.addFlashAttribute("usuario", new UsuarioDTO());
            ra.addFlashAttribute("error", false);
            return "redirect:/login";
        }
        ra.addFlashAttribute("entrenador", new EntrenadorDTO());
        ra.addFlashAttribute("disciplinas", svc.mostrarDisciplina());
        ra.addFlashAttribute("tituloPagina", "Registrarse como entrenador");
        ra.addFlashAttribute("mensaje", respuesta);
        return "redirect:/registro_entrenador";
    }
}
