package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepoEntrenador extends JpaRepository<Entrenador, Integer>{

	List<Entrenador> findAllByOrderByApellidopAsc();
	
	@Query(value = "SELECT * FROM Entrenador WHERE idusuario = :idusuario", nativeQuery = true)
	Entrenador findByIdusuario(@Param("idusuario") Integer idusuario);
	
	@Query(value = "SELECT * FROM Entrenador WHERE identrenador = :identrenador", nativeQuery = true)
	Entrenador findByIdEntrenador(@Param("identrenador") Integer idEntrenador);
	
	@Query(value = "SELECT * FROM Entrenador WHERE nombre = :nombre "
									  + "AND apellidop = :apellidop "
									  + "AND apellidom = :apellidom", nativeQuery = true)
	Entrenador findByNombreCompleto(@Param("nombre") String nombre,
							  @Param("apellidop") String apellidop,
							  @Param("apellidom") String apellidom);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Entrenador (nombre, apellidop, apellidom, idusuario) "
				 + "VALUES (:nombre, :apellidop, :apellidom, :idusuario)", nativeQuery = true)
	void agregarEntrenador(@Param("nombre") String nombre,
				   @Param("apellidop") String apellidop,
				   @Param("apellidom") String apellidom, 
				   @Param("idusuario") Integer idusuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Entrenador SET nombre = :nombre "
								 	   + "apellidop = :apellidop "
								 	   + "apellidom = :apellidom "
								 	   + "idusuario = :idusuario "
								 	   + "WHERE identrenador = :identrenador", nativeQuery = true)
	Integer actualizarEntrenador(@Param("nombre") String nombre,
						@Param("apellidop") String apellidop,
						@Param("apellidom") String apellidom, 
						@Param("idusuario") Integer idusuario,
						@Param("identrenador") Integer identrenador);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Entrenador WHERE identrenador = :identrenador", nativeQuery = true)
    void eliminarEntrenador(@Param("identrenador") Integer identrenador);
}
