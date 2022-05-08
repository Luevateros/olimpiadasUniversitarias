package com.shrek.olimpiadas.controlador;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shrek.olimpiadas.dto.CompetidorDTO;
import com.shrek.olimpiadas.servicio.SvcCompetidor;

@Controller
public class CtrlCompetidor {

	@Autowired
	private SvcCompetidor svc;
	
	@GetMapping("/menu_entrenador")
	public String mostrarCompetidores(Model model) {
 		model.addAttribute("competidores", svc.mostrarCompetidores());
		return "menu_entrenador";
	}
	
	@GetMapping("/menu_entrenador/nuevo")
	public String mostrarFormaNuevo(Model model) {
		model.addAttribute("competidor", new CompetidorDTO());
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("mostrarCuenta" , true);
		model.addAttribute("cuentaAnterior", null);
		model.addAttribute("correoAnterior", null);
		model.addAttribute("tituloPagina", "Agregar nuevo competidor");
		return "registro_competidor";
	}
	
	@PostMapping("/menu_entrenador/guardar")
	public String guardarCompetidor(CompetidorDTO competidor, RedirectAttributes ra, Model model) {
		
		// Al editar no se puede cambiar el número de cuenta.
		boolean mostrarCuenta = competidor.getCorreoViejo() == null;
		// Si hay un error después de intentar guardar un competidor
		// mostramos el mismo título que en el caso anterior. 
		String tituloDespuesError = mostrarCuenta ? "Agregar nuevo competidor" : 
			"Editar competidor (núm. cuenta: " + competidor.getIdcompetidor() + ")";
		boolean errorFecha = false;
		String respuesta= "";
		
		String formato = ""; 
		switch(competidor.getNacimiento().length()) {
			case 8:
				formato = "d/M/yyyy";
				break;
			case 9:
				formato = competidor.getNacimiento().indexOf("/") == 1 ? "dd/MM/yyyy" : "dd/M/yyyy";
				break;
			default:
				formato = "dd/MM/yyyy";}
		if(!GenericValidator.isDate(competidor.getNacimiento(), formato, true)) {
        	errorFecha = true;
        	respuesta = "Fecha de nacimiento inválida.";
		} else {
	        DateTimeFormatter formaDt = DateTimeFormatter.ofPattern(formato);
	        LocalDate nacimiento = LocalDate.parse(competidor.getNacimiento(), formaDt);
	        if(Period.between(nacimiento, LocalDate.now()).getYears() < 18){
		        	errorFecha = true;
		        	respuesta = "La edad mínima para participar es de 18 años.";
	        }
		}
        
        if(errorFecha) {
        	model.addAttribute("competidor", competidor);
			model.addAttribute("disciplinas", svc.mostrarDisciplina());
			model.addAttribute("mensaje", respuesta);
			model.addAttribute("mostrarCuenta" , mostrarCuenta);
			model.addAttribute("cuentaAnterior", mostrarCuenta ? null : competidor.getIdcompetidor());
			model.addAttribute("correoAnterior", mostrarCuenta ? null : competidor.getCorreoViejo());
			model.addAttribute("tituloPagina", tituloDespuesError);
			return "registro_competidor";
        }
                
        // Agregar competidor nuevo
        if(mostrarCuenta) {
        	respuesta = svc.agregarCompetidor(competidor);
    		if(respuesta == null) {
    			ra.addFlashAttribute("mensaje", "El competidor nuevo se agregó con exitó.");
    			return "redirect:/menu_entrenador";
    		}
        } 
        // Actualizar competidor
        else {
        	respuesta = svc.actualizarCompetidor(competidor);
    		if(respuesta == null) {
    			ra.addFlashAttribute("mensaje", "El competidor se actualizó con exitó.");
    			return "redirect:/menu_entrenador";
    		}
        }
		
		model.addAttribute("competidor", competidor);
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("mensaje", respuesta);
		model.addAttribute("mostrarCuenta" , mostrarCuenta);
		model.addAttribute("cuentaAnterior", mostrarCuenta ? null : competidor.getIdcompetidor());
		model.addAttribute("correoAnterior", mostrarCuenta ? null : competidor.getCorreoViejo());
		model.addAttribute("tituloPagina", tituloDespuesError);
		return "registro_competidor";
	}
	
	
	@GetMapping("/menu_entrenador/editar/{id}")
	public String actualizarCompetidor(@PathVariable(name = "id") String id, Model model, RedirectAttributes ra) {
		CompetidorDTO competidor = svc.getCompetidor(id);
		if(competidor == null) {
			ra.addFlashAttribute("mensaje", "No se encontró al competidor con número de cuenta " + id);
			return "redirect:/menu_entrenador";
		}
		model.addAttribute("competidor", competidor);
		model.addAttribute("disciplinas", svc.mostrarDisciplina());
		model.addAttribute("mostrarCuenta" , false);
		model.addAttribute("cuentaAnterior", competidor.getIdcompetidor());
		model.addAttribute("correoAnterior", competidor.getCorreoViejo());
		model.addAttribute("tituloPagina", "Editar competidor (Núm. cuenta: " + id + ")");
		return "registro_competidor";
	}
	
	@GetMapping("/menu_entrenador/eliminar/{id}")
	public String eliminarCompetidor(@PathVariable(name = "id") String id, Model model, RedirectAttributes ra) {
		CompetidorDTO competidor = svc.getCompetidor(id);
		if(competidor == null) {
			ra.addFlashAttribute("mensaje", "No se encontró al competidor con número de cuenta " + id);
			return "redirect:/menu_entrenador";
		}
		svc.eliminarCompetidor(id);
		ra.addFlashAttribute("mensaje", "El competidor con número de cuenta " + id + " se eliminó con exitó.");
		return "redirect:/menu_entrenador";
	}
	
}
