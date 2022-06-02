package com.shrek.olimpiadas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrtlAdmin {
	
	@GetMapping("/admin")
	public String mostrarMenu(){
        return "menu_admin";
	}
	
}
