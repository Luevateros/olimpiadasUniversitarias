package com.shrek.olimpiadas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shrek.olimpiadas.dto.CompetidorDTO;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.modelo.TipoUsuario;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoCalificacion;
import com.shrek.olimpiadas.repositorio.RepoCompetidor;
import com.shrek.olimpiadas.repositorio.RepoDisciplina;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.servicio.SvcCompetidor;

@Service
public class SvcCompetidorImpl implements SvcCompetidor{

	@Autowired
	private RepoCompetidor repoCompetidor;
	
	@Autowired
	private RepoUsuario repoUsuario;
	
	@Autowired
	private RepoDisciplina repoDisciplina;
	
	@Autowired
	private RepoCalificacion repoCalificacion;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Competidor> mostrarTodosCompetidores(){
		return repoCompetidor.findAll();
	}
	
	@Override
	public List<Competidor> mostrarCompetidores(Integer identrenador){
		return (List<Competidor>) repoCompetidor.mostrarCompetidores(identrenador);
	}
	
	@Override
	public List<Disciplina> mostrarDisciplina(){
		return (List<Disciplina>) repoDisciplina.findAll();
	}
	
	@Override
	public String agregarCompetidor(CompetidorDTO competidor, Integer identrenador){
		
		Usuario usuarioNuevo = (Usuario) repoUsuario.findByCorreo(competidor.getCorreoNuevo());
		if(usuarioNuevo != null)
			return "El correo está en uso.";
		
		Competidor competidorNuevo = (Competidor) repoCompetidor.findByIdcompetidor(competidor.getIdcompetidor());
		if(competidorNuevo != null)
			return "El número de cuenta está en uso.";
		
		usuarioNuevo = new Usuario();
		usuarioNuevo.setCorreo(competidor.getCorreoNuevo());
		//usuarioNuevo.setPassword(passwordEncoder.encode(competidor.getPassword()));
		usuarioNuevo.setPassword(competidor.getPassword());
		usuarioNuevo.setTipousuario(TipoUsuario.COMPETIDOR);
		
		Usuario usuarioGuardado = repoUsuario.save(usuarioNuevo);
		
		int iddisciplina = 0;
		try {
			iddisciplina = Integer.parseInt(competidor.getDisciplina());
		} catch (NumberFormatException e) {
			iddisciplina = 0;
		}
		
		repoCompetidor.agregarCompetidor(
				competidor.getIdcompetidor(),
				competidor.getNombre(),
				competidor.getApellidop(),
				competidor.getApellidom(),
				competidor.getSexo(),
				competidor.getNacimiento(),
				competidor.getEscuela(),
				usuarioGuardado.getIdusuario(),
				iddisciplina);
				
		repoCompetidor.agregarAsesorar(competidor.getIdcompetidor(), identrenador);
		
		return null;
	}
	
	@Override
	public String actualizarCompetidor(CompetidorDTO competidor) {

		if (!competidor.getCorreoNuevo().equals(competidor.getCorreoViejo())) {
			Usuario usuario = (Usuario) repoUsuario.findByCorreo(competidor.getCorreoNuevo());
			if(usuario != null)
				return "El correo está en uso.";
		}
		
		// El correo es el mismo pero actualizamos al usuario por si la contraseña cambió.
		Usuario usuarioAnterior = (Usuario) repoUsuario.findByCorreo(competidor.getCorreoViejo());
				
		repoUsuario.actualizarUsuario(	
				competidor.getCorreoNuevo(),
				passwordEncoder.encode(competidor.getPassword()),
				TipoUsuario.COMPETIDOR.toString(),
				usuarioAnterior.getIdusuario());
				
		int iddisciplina = 0;
		try {
			iddisciplina = Integer.parseInt(competidor.getDisciplina());
		} catch (NumberFormatException e) {
			iddisciplina = 0;
		}
		
		repoCompetidor.actualizarCompetidor(	
				competidor.getIdcompetidor(),
				competidor.getNombre(),
				competidor.getApellidop(),
				competidor.getApellidom(),
				competidor.getSexo(),
				competidor.getNacimiento(),
				competidor.getEscuela(),
				usuarioAnterior.getIdusuario(), 
				iddisciplina);
		
		return null;
	}
	
	@Override
	public CompetidorDTO getCompetidor(String id) {
		Competidor competidor = repoCompetidor.findByIdcompetidor(id);
		if(competidor == null)
			return null;
		CompetidorDTO dto = new CompetidorDTO();
		dto.setIdcompetidor(competidor.getIdcompetidor());
		dto.setNombre(competidor.getNombre());
		dto.setApellidop(competidor.getApellidop());
		dto.setApellidom(competidor.getApellidom());
		dto.setSexo(competidor.getSexo());
		dto.setNacimiento(competidor.getNacimiento());
		dto.setEscuela(competidor.getEscuela());
		dto.setCorreoNuevo(competidor.getUsuario().getCorreo());
		dto.setCorreoViejo(competidor.getUsuario().getCorreo());
		dto.setPassword(competidor.getUsuario().getPassword());
		dto.setDisciplina(competidor.getDisciplina());
		return dto;
	}
	
	@Override
	public String eliminarCompetidor(String idcompetidor){
		Integer calificaciones = repoCalificacion.calificacionesCompetidor(idcompetidor);
		if (calificaciones == 0) {
			Competidor competidor = (Competidor) repoCompetidor.findByIdcompetidor(idcompetidor);
			repoCompetidor.eliminarAsesorar(idcompetidor);
			repoCompetidor.eliminarCompetidor(idcompetidor);
			repoUsuario.eliminarUsuario(competidor.getUsuario().getIdusuario());
            return null;
        }
		return "No se puede eliminar al competidor con núm. de cuenta " + idcompetidor + ", porque ya fue calificado.";
		
	}
	
}
