package com.shrek.olimpiadas.servicio;

import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.modelo.Disciplina;

import java.util.List;

public interface SvcEntrenador {
    List<Disciplina> mostrarDisciplina();
    String agregarEntrenador(EntrenadorDTO entrenador);
}
