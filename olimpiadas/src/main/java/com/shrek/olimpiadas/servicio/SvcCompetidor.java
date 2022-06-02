package com.shrek.olimpiadas.servicio;

import java.util.List;

import com.shrek.olimpiadas.dto.CompetidorDTO;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Disciplina;

public interface SvcCompetidor {
	List<Competidor> mostrarTodosCompetidores();
	List<Competidor> mostrarCompetidores(Integer identrenador);
	List<Disciplina> mostrarDisciplina();
	String agregarCompetidor(CompetidorDTO competidor, Integer identrenador);
	String actualizarCompetidor(CompetidorDTO competidor);
	void eliminarCompetidor(String idcompetidor);
	CompetidorDTO getCompetidor(String id);
}
