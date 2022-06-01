package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.Calificacion;
import com.shrek.olimpiadas.modelo.CalificacionCompetidorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepoCalificacion extends JpaRepository<Calificacion, Integer>{

	//List<Calificacion> findAllByOrderByIdCompetidorAsc();
	
	@Query(value = "SELECT * FROM Calificacion WHERE idcompetidor = :idcompetidor", nativeQuery = true)
	List<Calificacion> findByIdcompetidor(@Param("idcompetidor") String idCompetidor);
	
	@Query(value = "SELECT * FROM Calificacion WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	List<Calificacion> findByiddisciplina(@Param("iddisciplina") Integer iddisciplina);

	@Query(value = "SELECT c.nombre, c.apellidop, c.apellidom, c.escuela, c.sexo, k.calificacion "
			+ "FROM competidor c "
			+ "JOIN calificacion k "
			+ "ON c.idcompetidor = k.idcompetidor "
			+ "WHERE iddisciplina = :iddisciplina AND sexo = :sexo "
			+ "ORDER BY k.calificacion Desc "
			+ " ", nativeQuery = true)
	List<CalificacionCompetidorDto> traeCalificaciones(
			@Param("iddisciplina") Integer iddisciplina,
			@Param("sexo") Integer sexo);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Calificacion (comentario, calificacion, idcompetidor, idjuez) "
				 + "VALUES (:comentario, :calificacion, :idcompetidor, :idjuez)", nativeQuery = true)
	void crearCalificacion(@Param("comentario") String comentario,
						   @Param("calificacion") Float calificacion,
						   @Param("idcompetidor") Integer idCompetidor,
						   @Param("idjuez") Integer idJuez);
	
}
