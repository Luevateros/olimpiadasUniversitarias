package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Entrenador;
import com.shrek.olimpiadas.modelo.Juez;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoCalificacionEntrenadorDto;
import com.shrek.olimpiadas.repositorio.RepoEntrenador;
import com.shrek.olimpiadas.repositorio.RepoJuez;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.repositorio.RepoCompetidor;
import com.shrek.olimpiadas.servicio.SvcCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private RepoEntrenador repoEntrenador;
    @Autowired
    private RepoCalificacionEntrenadorDto repoCalificacionEntrenadorDto;


    @RequestMapping("/entrenador_calificaciones")
    public String consultarCalificacionesEntrenador(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        if (name.compareTo("anonymousUser") != 0) {
            Usuario usuario = repoUsuario.findByCorreo(name);
            System.out.println(usuario);
            if (usuario != null) {
                Entrenador entrenador = repoEntrenador.findByIdusuario(usuario.getIdusuario());
                if (entrenador != null) {
                    model.addAttribute("calificaciones", svcCalificacion.traeCalificacionesEntrenador(entrenador.getIdentrenador()));
                    return "calificaciones_entrenador";
                }
            }
        }
        return "redirect:/login";
    }

    @Autowired
    private RepoCompetidor repoCompt;

    @GetMapping("/menu_competidor")
    public String mostrarComentarios(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null) {
                    Juez juez = repoJuez.findByidusuario(usuario.getIdusuario());
                    model.addAttribute("comentarios", svcCalificacion.traeComentarios(juez.getIdjuez()));
                    return "menu_competidor";
                }
            }
        }
        return "menu_competidor";
    }

    @RequestMapping("/menu_competidor/calificacion_perso")
    public String mostrarCalificacionPerso(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null) {
                    Competidor competidor = repoCompt.findByidusuario(usuario.getIdusuario());
                    if(competidor != null) {
                        model.addAttribute("calificaciones", svcCalificacion.mostrarCalificacionPerso(competidor.getIdcompetidor()));
                        return "calificacion_perso";
                    }
                }
            }
        }
        return "calificacion_perso";
    }

    @RequestMapping("/menu_competidor/posiciones")
    public String tablaPosiciones(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null) {
                    Juez juez = repoJuez.findByidusuario(usuario.getIdusuario());
                    if(juez != null) {
                        model.addAttribute("posiciones", svcCalificacion.traeCalificaciones(juez.getIdjuez()));
                        return "posiciones";
                    }
                }
            }
        }
        return "posiciones";
    }

    @GetMapping("/menu_juez")
    public String registroEntrenador(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null){
                    Juez juez = repoJuez.findByidusuario(usuario.getIdusuario());
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
                    Juez juez = repoJuez.findByidusuario(usuario.getIdusuario());
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
