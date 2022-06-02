package com.shrek.olimpiadas.servicio;

import java.util.List;

import com.shrek.olimpiadas.dto.JuezDTO;
import com.shrek.olimpiadas.modelo.Juez;
import com.shrek.olimpiadas.modelo.Disciplina;

public interface SvcJuez {
	List<Juez> mostrarJueces();
	List<Disciplina> mostrarDisciplina();
	String agregarJuez(JuezDTO juez);
	String actualizarJuez(JuezDTO juez);
	void eliminarJuez(Integer idjuez);
	JuezDTO getJuez(Integer id);
}
