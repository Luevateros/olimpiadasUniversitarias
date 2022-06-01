package com.shrek.olimpiadas.servicio;
import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.CalificacionEntrenadorDto;
import com.shrek.olimpiadas.modelo.CalificacionCompetidorDto;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Calificacion;

import java.util.List;

public interface SvcCalificacion {
    List<Calificacion> traeComentarios(String id);
    List<CalificacionCompetidorDto> traeCalificaciones(Integer id, Integer sexo);
    List<Calificacion> mostrarCalificacionPerso(String id);
    List<Competidor> traeCompetidores(Integer id);
    String agregarCalificacion(CalificacionDto calificacion);
    List<CalificacionEntrenadorDto> traeCalificacionesEntrenador(Integer id);
}
