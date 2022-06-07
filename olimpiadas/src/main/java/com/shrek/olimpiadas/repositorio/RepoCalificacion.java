package com.shrek.olimpiadas.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shrek.olimpiadas.modelo.Calificacion;
import com.shrek.olimpiadas.modelo.CalificacionCompetidorDto;

@Repository
public interface RepoCalificacion extends JpaRepository<Calificacion, Integer>{
	
	@Query(value = "SELECT * FROM Calificacion WHERE idcompetidor = :idcompetidor", nativeQuery = true)
	List<Calificacion> findByIdcompetidor(@Param("idcompetidor") String idcompetidor);
		
	@Query(name = "traeCalificaciones", nativeQuery = true)
	List<CalificacionCompetidorDto> traeCalificaciones(
					@Param("iddisciplina") Integer iddisciplina,
					@Param("sexo") Integer sexo);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Calificacion (comentario, calificacion, idcompetidor, idjuez, iddisciplina) "
                    + "VALUES (:comentario, :calificacion, :idcompetidor, :idjuez, :iddisciplina)", nativeQuery = true)
	void crearCalificacion(
                        @Param("comentario") String comentario,
                        @Param("calificacion") Float calificacion,
                        @Param("idcompetidor") String idcompetidor,
                        @Param("idjuez") Integer idjuez,
                        @Param("iddisciplina") Integer iddisciplina);
	
	@Query(value = "SELECT COUNT(idcompetidor) FROM Calificacion  WHERE idcompetidor = :idcompetidor", nativeQuery = true)
	Integer calificacionesCompetidor(@Param("idcompetidor") String idcompetidor);
	
	@Query(value = "SELECT COUNT(idjuez) FROM Calificacion  WHERE idjuez = :idjuez", nativeQuery = true)
	Integer calificacionesJuez(@Param("idjuez") Integer idjuez);
	
}
