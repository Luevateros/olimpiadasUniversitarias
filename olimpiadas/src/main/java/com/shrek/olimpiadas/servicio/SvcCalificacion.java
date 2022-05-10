package com.shrek.olimpiadas.servicio;
import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.modelo.Competidor;

import java.util.List;

public interface SvcCalificacion {
    List<Competidor> traeCompetidores(Integer id);
     String agregarCalificacion(CalificacionDto calificacion);
}
