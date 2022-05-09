package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.Calificacion;
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
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Calificacion (comentario, calificacion, idcompetidor, idjuez, iddisciplina) "
				 + "VALUES (:comentario, :calificacion, :idcompetidor, :idjuez, :iddisciplina)", nativeQuery = true)
	void crearCalificacion(@Param("comentario") String nombre,
						   @Param("calificacion") Float calificacion,
						   @Param("idcompetidor") String idCompetidor,
						   @Param("idjuez") Integer idJuez,
						   @Param("iddisciplina") Integer iddisciplina);
	
}
