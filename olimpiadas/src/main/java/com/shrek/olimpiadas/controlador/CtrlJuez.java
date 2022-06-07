package com.shrek.olimpiadas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shrek.olimpiadas.dto.JuezDTO;
import com.shrek.olimpiadas.servicio.SvcJuez;

@Controller
public class CtrlJuez {
	
	@Autowired
	private SvcJuez svc;
	
	@GetMapping("/jueces")
	public String mostrarJueces(Model model){
		model.addAttribute("jueces", svc.mostrarJueces());
        return "crud_juez";
	}
	
	@GetMapping("/juez/nuevo")
	public String mostrarFormaNuevo(Model model) {
		model.addAttribute("juez", new JuezDTO());
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("nuevo" , true);
		model.addAttribute("correoAnterior", null);
		model.addAttribute("tituloPagina", "Agregar nuevo juez");
		return "registro_juez";
	}
	
	@PostMapping("/juez/guardar")
	public String guardarJuez(JuezDTO juez, RedirectAttributes ra, Model model) {
		boolean nuevo = juez.getCorreoViejo() == null;
		String respuesta= "";
                
        // Agregar juez nuevo
        if(nuevo) {
        	respuesta = svc.agregarJuez(juez);
    		if(respuesta == null) {
    			ra.addFlashAttribute("mensaje", "El juez nuevo se agregó con éxito.");
    			return "redirect:/jueces";
    		}
        } 
        // Actualizar juez
        else {
        	respuesta = svc.actualizarJuez(juez);
    		if(respuesta == null) {
    			ra.addFlashAttribute("mensaje", "El juez se actualizó con éxito.");
    			return "redirect:/jueces";
    		}
        }
		
		model.addAttribute("juez", juez);
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("mensaje", respuesta);
		model.addAttribute("nuevo", nuevo);
		model.addAttribute("correoAnterior", nuevo ? null : juez.getCorreoViejo());
		model.addAttribute("tituloPagina",   nuevo ? "Agregar nuevo juez" : "Editar juez");
		return "registro_juez";
	}
	
	@GetMapping("/juez/editar/{id}")
	public String actualizarJuez(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		JuezDTO juez = svc.getJuez(id);
		if(juez == null) {
			ra.addFlashAttribute("mensaje", "No se encontró al juez con id " + id);
			return "redirect:/jueces";
		}
		model.addAttribute("juez", juez);
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("nuevo" , false);
		model.addAttribute("correoAnterior", juez.getCorreoViejo());
		model.addAttribute("tituloPagina", "Editar juez");
		return "registro_juez";
	}
	
	@GetMapping("/juez/eliminar/{id}")
	public String eliminarJuez(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		JuezDTO juez = svc.getJuez(id);
		if(juez == null) {
			ra.addFlashAttribute("mensaje", "No se encontró al juez con id " + id);
			return "redirect:/jueces";
		}
		String respuesta = svc.eliminarJuez(id);
		String mensaje = (respuesta == null) ? "El juez se eliminó con éxito." : respuesta;
		ra.addFlashAttribute("mensaje", mensaje);
		return "redirect:/jueces";
	}
	
}
