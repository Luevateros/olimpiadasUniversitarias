package com.shrek.olimpiadas.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Entrenador;
import com.shrek.olimpiadas.modelo.Juez;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoCompetidor;
import com.shrek.olimpiadas.repositorio.RepoEntrenador;
import com.shrek.olimpiadas.repositorio.RepoJuez;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.servicio.SvcCalificacion;

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
    private RepoCompetidor repoCompt;

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
                		model.addAttribute("entrenador", entrenador);
                    model.addAttribute("calificaciones", svcCalificacion.traeCalificacionesEntrenador(entrenador.getIdentrenador()));
                    return "calificaciones_entrenador";
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/competidor")
    public String competidorCalificacion(Model model, Principal principal) {
        if(principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        Usuario usuario = repoUsuario.findByCorreo(name);
        Competidor competidor = repoCompt.findByIdusuario(usuario.getIdusuario());
        System.out.println(competidor);
        model.addAttribute("nombre", competidor.getNombre());
        model.addAttribute("apellidop", competidor.getApellidop());
        model.addAttribute("apellidom", competidor.getApellidom());
        model.addAttribute("disciplina", competidor.getDisciplina());
        model.addAttribute("comentarios", svcCalificacion.competidorCalificacion(competidor.getIdcompetidor()));
        return "menu_competidor";
    }

    @RequestMapping("/competidor/posiciones")
    public String tablaPosiciones(Model model, Principal principal) {
        if(principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        Usuario usuario = repoUsuario.findByCorreo(name);
        Competidor competidor = repoCompt.findByIdusuario(usuario.getIdusuario());
        model.addAttribute("disciplina", competidor.getDisciplina());
        model.addAttribute("sexo", competidor.getSexo());
        model.addAttribute("posiciones", svcCalificacion.traeCalificaciones(competidor.getIddisciplina(), competidor.getSexo()));
        return "posiciones";
    }
    
    @GetMapping("/juez")
    public String registroEntrenador(Model model, Principal principal) {
        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null){
                    Juez juez=  repoJuez.findByIdusuario(usuario.getIdusuario());
                    if(juez != null){
                    		model.addAttribute("disciplina", juez.getDisciplina());
                    		model.addAttribute("juez", juez);
                    		model.addAttribute("competidores", svcCalificacion.traeCompetidores(juez.getIdjuez()));
                        return "menu_juez";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/juez/calificar/{id}")
    public String calificarCompetidor(@PathVariable(name = "id") String id, Model model, RedirectAttributes ra, Principal principal) {

        if(principal != null){
            String name = principal.getName();
            if(name != null){
                Usuario usuario = repoUsuario.findByCorreo(name);
                if(usuario != null){
                    Juez juez=  repoJuez.findByIdusuario(usuario.getIdusuario());
                    if(juez != null){
                        CalificacionDto calificacionDto = new CalificacionDto();
                        calificacionDto.setIdcompetidor(id);
                        calificacionDto.setIdjuez(juez.getIdjuez());
                        Competidor competidor = repoCompt.findByIdcompetidor(id);
                        calificacionDto.setIddisciplina(competidor.getIddisciplina());
                        model.addAttribute("calificacion", calificacionDto);
                        return "calificar";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/juez/calificar/guardar")
    public String crearCalificacion(CalificacionDto calificacion, RedirectAttributes ra, Model model) {
        String respuesta = svcCalificacion.agregarCalificacion(calificacion);
        if(respuesta == null) {
            model.addAttribute("mensaje", "Registro completo con exito");
            model.addAttribute("competidores", svcCalificacion.traeCompetidores(calificacion.getIdjuez()));
            return "redirect:/juez";
        }
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setIdcompetidor(calificacion.getIdcompetidor());
        calificacionDto.setIdjuez(calificacion.getIdjuez());
        calificacionDto.setIddisciplina(calificacion.getIddisciplina());
        model.addAttribute("calificacion", calificacionDto);
        model.addAttribute("mensaje", respuesta);
        return "calificar";
    }
}
