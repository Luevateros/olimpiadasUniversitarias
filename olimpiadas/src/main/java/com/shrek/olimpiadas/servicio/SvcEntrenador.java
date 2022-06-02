package com.shrek.olimpiadas.servicio;

import java.util.List;

import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.modelo.Entrenador;

public interface SvcEntrenador {
	List<Entrenador> mostrarEntrenadores();
    List<Disciplina> mostrarDisciplina();
    String agregarEntrenador(EntrenadorDTO entrenador);
    Entrenador getEntrenador(Integer id);
}
