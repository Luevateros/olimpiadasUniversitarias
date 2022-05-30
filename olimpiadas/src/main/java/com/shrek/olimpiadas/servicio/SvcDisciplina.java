package com.shrek.olimpiadas.servicio;

import com.shrek.olimpiadas.dto.DisciplinaDTO;
import com.shrek.olimpiadas.modelo.Disciplina;

import java.util.List;

public interface SvcDisciplina {
    List<Disciplina> mostrarDisciplinas();
    DisciplinaDTO mostrarDisciplina(Integer iddisciplina);
    String agregarDisciplina(DisciplinaDTO disciplina);
    String actualizarDisciplina(DisciplinaDTO disciplina);
    String eliminarDisciplina(Integer iddisciplina);
}
