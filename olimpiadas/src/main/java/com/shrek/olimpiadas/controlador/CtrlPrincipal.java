package com.shrek.olimpiadas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CtrlPrincipal {

	@GetMapping("")
	public String verPantallaPrincioal() {
		return "index";
	}
}
