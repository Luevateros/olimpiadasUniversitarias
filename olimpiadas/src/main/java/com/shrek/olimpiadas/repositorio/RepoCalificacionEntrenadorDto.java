package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.CalificacionEntrenadorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCalificacionEntrenadorDto extends JpaRepository<CalificacionEntrenadorDto, Integer> {

    @Query(value = "SELECT calificacion.idcalificacion,\n" +
            "competidor.nombre as nombre,\n" +
            "competidor.apellidop, competidor.apellidom, \n" +
            "juez.nombre as juez, calificacion, disciplina.nombre as disciplina\n" +
            "FROM calificacion INNER JOIN asesorar \n" +
            "ON calificacion.idcompetidor = asesorar.idcompetidor \n" +
            "INNER JOIN competidor \n" +
            "ON calificacion.idcompetidor = asesorar.idcompetidor \n" +
            "INNER JOIN disciplina \n" +
            "ON disciplina.iddisciplina = disciplina.iddisciplina \n" +
            "INNER JOIN juez \n" +
            "ON juez.idjuez = calificacion.idjuez \n" +
            "WHERE asesorar.identrenador = 1;", nativeQuery = true)
    List<CalificacionEntrenadorDto> mostrarCalificaciones(@Param("identrenador") Integer identrenador);

}
