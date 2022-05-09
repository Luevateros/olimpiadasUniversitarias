package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.CompetidorDTO;
import com.shrek.olimpiadas.dto.UsuarioDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CtrlPrincipal {

	@GetMapping("")
	public String verPantallaPrincioal(Model model) {
		return "index";
	}

	@RequestMapping("/login")
	public String index(Model model, String error, Principal principal) {
		model.addAttribute("usuario", new UsuarioDTO());
		if (error != null) {
			System.out.println(error);
			model.addAttribute("error", true);
		}

		if (principal != null) {
			return "index";
		}
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "logout";
	}

}
