package com.shrek.olimpiadas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shrek.olimpiadas.dto.JuezDTO;
import com.shrek.olimpiadas.modelo.Juez;
import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.modelo.TipoUsuario;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoJuez;
import com.shrek.olimpiadas.repositorio.RepoDisciplina;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.servicio.SvcJuez;

@Service
public class SvcJuezImpl implements SvcJuez {

	@Autowired
	private RepoJuez repoJuez;
	
	@Autowired
	private RepoUsuario repoUsuario;
	
	@Autowired
	private RepoDisciplina repoDisciplina;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Juez> mostrarJueces() {
		return repoJuez.findAll();
	}

	@Override
	public List<Disciplina> mostrarDisciplina() {
		return (List<Disciplina>) repoDisciplina.findAll();
	}

	@Override
	public String agregarJuez(JuezDTO juez) {
		Usuario usuarioNuevo = (Usuario) repoUsuario.findByCorreo(juez.getCorreoNuevo());
		if(usuarioNuevo != null)
			return "El correo está en uso.";
	
		usuarioNuevo = new Usuario();
		usuarioNuevo.setCorreo(juez.getCorreoNuevo());
		usuarioNuevo.setPassword(passwordEncoder.encode(juez.getPassword()));
		usuarioNuevo.setTipousuario(TipoUsuario.JUEZ);
		
		Usuario usuarioGuardado = repoUsuario.save(usuarioNuevo);
		
		int iddisciplina = 0;
		try {
			iddisciplina = Integer.parseInt(juez.getDisciplina());
		} catch (NumberFormatException e) {
			iddisciplina = 0;
		}
		
		repoJuez.agregarJuez(
				juez.getNombre(),
				juez.getApellidop(),
				juez.getApellidom(),
				usuarioGuardado.getIdusuario(),
				iddisciplina);
		
		return null;
	}

	@Override
	public String actualizarJuez(JuezDTO juez) {
		if (!juez.getCorreoNuevo().equals(juez.getCorreoViejo())) {
			Usuario usuario = (Usuario) repoUsuario.findByCorreo(juez.getCorreoNuevo());
			if(usuario != null)
				return "El correo está en uso.";
		}
		
		// El correo es el mismo pero actualizamos al usuario por si la contraseña cambió.
		Usuario usuarioAnterior = (Usuario) repoUsuario.findByCorreo(juez.getCorreoViejo());
		
		repoUsuario.actualizarUsuario(	
				juez.getCorreoNuevo(),
				passwordEncoder.encode(juez.getPassword()),
				TipoUsuario.JUEZ.toString(),
				usuarioAnterior.getIdusuario());
				
		int iddisciplina = 0;
		try {
			iddisciplina = Integer.parseInt(juez.getDisciplina());
		} catch (NumberFormatException e) {
			iddisciplina = 0;
		}
		
		repoJuez.actualizarJuez(	
				juez.getIdjuez(),
				juez.getNombre(),
				juez.getApellidop(),
				juez.getApellidom(),
				usuarioAnterior.getIdusuario(), 
				iddisciplina);
				
		return null;
	}

	@Override
	public void eliminarJuez(Integer idjuez) {
		Juez juez = (Juez) repoJuez.findByIdjuez(idjuez);
		repoJuez.eliminarJuez(idjuez);
		repoUsuario.eliminarUsuario(juez.getUsuario().getIdusuario());
	}

	@Override
	public JuezDTO getJuez(Integer id) {
		Juez juez = repoJuez.findByIdjuez(id);
		if(juez == null)
			return null;
		JuezDTO dto = new JuezDTO();
		dto.setIdjuez(juez.getIdjuez());
		dto.setNombre(juez.getNombre());
		dto.setApellidop(juez.getApellidop());
		dto.setApellidom(juez.getApellidom());
		dto.setCorreoNuevo(juez.getUsuario().getCorreo());
		dto.setCorreoViejo(juez.getUsuario().getCorreo());
		dto.setPassword(juez.getUsuario().getPassword());
		dto.setDisciplina(juez.getDisciplina());
		return dto;
	}

}
