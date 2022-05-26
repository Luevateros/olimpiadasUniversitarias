package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.Juez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepoJuez extends JpaRepository<Juez, Integer> {

	List<Juez> findAllByOrderByApellidopAsc();
	
	@Query(value = "SELECT * FROM Juez WHERE idjuez = :idjuez", nativeQuery = true)
	Juez findByidjuez(@Param("idjuez") Integer idjuez);
	
	@Query(value = "SELECT * FROM Juez WHERE nombre = :nombre "
									  + "AND apellidop = :apellidop "
									  + "AND apellidom = :apellidom", nativeQuery = true)
	Juez findByNombreCompleto(@Param("nombre") String nombre,
							  @Param("apellidop") String apellidop,
							  @Param("apellidom") String apellidom);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Juez (nombre, apellidop, apellidom, iddisciplina, idusuario) "
				 + "VALUES (:nombre, :apellidop, :apellidom, :iddisciplina, :idusuario)", nativeQuery = true)
	void crearJuez(@Param("nombre") String nombre,
				   @Param("apellidop") String apellidop,
				   @Param("apellidom") String apellidom, 
				   @Param("iddisciplina") Integer iddisciplina,
				   @Param("idusuario") Integer idusuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Juez SET nombre = :nombre "
								 + "apellidop = :apellidop "
								 + "apellidom = :apellidom "
								 + "iddisciplina = :iddisciplina "
								 + "idusuario = :idusuario "
								 + "WHERE idjuez = :idjuez", nativeQuery = true)
	Integer actualizarJuez(@Param("nombre") String nombre,
						@Param("apellidop") String apellidop,
						@Param("apellidom") String apellidom, 
						@Param("iddisciplina") Integer iddisciplina,
						@Param("idusuario") Integer idusuario,
						@Param("idjuez") Integer idjuez);
	
	@Modifying
	@Transactional
    @Query(value="DELETE FROM Juez WHERE idjuez = :idjuez", nativeQuery = true)
    void eliminarJuez(@Param("idjuez") Integer idjuez);

	@Query(value = "SELECT * FROM Juez WHERE idusuario = :idusuario", nativeQuery = true)
	Juez findByidusuario(@Param("idusuario") Integer idusuario);
}
