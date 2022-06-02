package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.UsuarioDTO;
import com.shrek.olimpiadas.modelo.TipoUsuario;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CtrlPrincipal {

	@Autowired
    private RepoUsuario repoUsuario;
	
	@GetMapping({"/login", "/", " * "})
	public String inicio(Model model, String error, Principal principal) {
		model.addAttribute("usuario", new UsuarioDTO());
		if (error != null) {
			System.out.println(error);
			model.addAttribute("error", true);
		}
		if (principal != null) {
			Usuario usuario = repoUsuario.findByCorreo(principal.getName());
			if(usuario.getTipousuario() == TipoUsuario.ADMIN)
				return "menu_admin";
			if(usuario.getTipousuario() == TipoUsuario.ENTRENADOR)
				return "redirect:/competidores";
			if(usuario.getTipousuario() == TipoUsuario.JUEZ)
				return "redirect:/juez";
			if(usuario.getTipousuario() == TipoUsuario.COMPETIDOR)
				return "redirect:/competidor";
			System.out.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>> \n "
								+ "inicio() > Esto no debería verse \n "
								+ ">>>>>>>>>>>>>>>>>>>>>>>> \n\n ");
			return "login";
		}
		return "login";
	}

	@GetMapping("/logout")
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
		return "login";
	}

}
