package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Juez;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoJuez;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.servicio.SvcCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class CtrlCalificaciones {
    @Autowired
    private SvcCalificacion svcCalificacion;
    @Autowired
    private RepoUsuario repoUsuario;
    @Autowired
    private RepoJuez repoJuez;

    @GetMapping("/menu_juez")
    public String registroEntrenador(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null){
                    Juez juez=  repoJuez.findByidusuario(usuario.getIdusuario());
                    if(juez != null){
                        model.addAttribute("competidores", svcCalificacion.traeCompetidores(juez.getIdjuez()));
                        return "menu_juez";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/menu_juez/calificar/{id}")
    public String eliminarCompetidor(@PathVariable(name = "id") String id, Model model, RedirectAttributes ra, Principal principal) {

        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null){
                    Juez juez=  repoJuez.findByidusuario(usuario.getIdusuario());
                    if(juez != null){
                        CalificacionDto calificacionDto = new CalificacionDto();
                        calificacionDto.setIdcompetidor(id);
                        calificacionDto.setIdjuez(juez.getIdjuez());
                        model.addAttribute("calificacion", calificacionDto);
                        return "calificar";
                    }
                }
            }
        }
        return "redirect:/login";
    }
    @PostMapping("/menu_juez/calificar/guardar")
    public String crearCalificacion(CalificacionDto calificacion, RedirectAttributes ra, Model model) {
        String respuesta = svcCalificacion.agregarCalificacion(calificacion);
        if(respuesta == null) {
            model.addAttribute("mensaje", "Registro completo con exito");
            model.addAttribute("competidores", svcCalificacion.traeCompetidores(calificacion.getIdjuez()));
            return "redirect:/menu_juez";
        }
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setIdcompetidor(calificacion.getIdcompetidor());
        calificacionDto.setIdjuez(calificacion.getIdjuez());
        model.addAttribute("calificacion", calificacionDto);
        model.addAttribute("mensaje", respuesta);
        return "calificar";
    }
}
