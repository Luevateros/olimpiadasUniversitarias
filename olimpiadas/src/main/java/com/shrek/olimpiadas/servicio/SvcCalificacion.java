package com.shrek.olimpiadas.servicio;
import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Calificacion;

import java.util.List;

public interface SvcCalificacion {
    List<Calificacion> traeComentarios(Integer id);
    List<Calificacion> traeCalificaciones(Integer id);
    List<Calificacion> mostrarCalificacionPerso(String id);
    List<Competidor> traeCompetidores(Integer id);
    String agregarCalificacion(CalificacionDto calificacion);
}
