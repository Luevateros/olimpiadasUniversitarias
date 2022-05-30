package com.shrek.olimpiadas.servicio;
import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.CalificacionEntrenadorDto;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Calificacion;

import java.util.List;

public interface SvcCalificacion {
    List<Calificacion> traeComentarios(Integer id);
    List<Calificacion> traeCalificaciones(Integer id);
    List<Calificacion> mostrarCalificacionPerso(String id);
    List<Competidor> traeCompetidores(Integer id);
    String agregarCalificacion(CalificacionDto calificacion);
<<<<<<< HEAD
=======
    List<CalificacionEntrenadorDto> traeCalificacionesEntrenador(Integer id);
>>>>>>> 19595a8cbdae4b4201477b44fd1e1934cdaa05a3
}
