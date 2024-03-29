package com.shrek.olimpiadas.servicio;
import java.util.List;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Calificacion;
import com.shrek.olimpiadas.modelo.CalificacionCompetidorDto;
import com.shrek.olimpiadas.modelo.CalificacionEntrenadorDto;
import com.shrek.olimpiadas.modelo.Competidor;

public interface SvcCalificacion {
    List<Calificacion> competidorCalificacion(String id);
    List<CalificacionCompetidorDto> traeCalificaciones(Integer id, Integer sexo);
    List<Competidor> traeCompetidores(Integer id);
    String agregarCalificacion(CalificacionDto calificacion);
    List<CalificacionEntrenadorDto> traeCalificacionesEntrenador(Integer id);
}
