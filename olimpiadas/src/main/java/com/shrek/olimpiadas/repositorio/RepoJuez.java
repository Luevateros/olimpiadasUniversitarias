package com.shrek.olimpiadas.repositorio;

import com.shrek.olimpiadas.modelo.Juez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepoJuez extends JpaRepository<Juez, Integer> {
	
	@Query(value = "SELECT * FROM Juez WHERE idjuez = :idjuez", nativeQuery = true)
	Juez findByIdjuez(@Param("idjuez") Integer idjuez);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Juez (nombre, apellidop, apellidom, idusuario, iddisciplina) "
				 + "VALUES (:nombre, :apellidop, :apellidom, :idusuario, :iddisciplina)", nativeQuery = true)
	void agregarJuez(@Param("nombre") String nombre,
				   @Param("apellidop") String apellidop,
				   @Param("apellidom") String apellidom,
				   @Param("idusuario") Integer idusuario, 
				   @Param("iddisciplina") Integer iddisciplina);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Juez SET idjuez = :idjuez,"
								+ "nombre = :nombre, "
								+ "apellidop = :apellidop, "
								+ "apellidom = :apellidom, "
								+ "iddisciplina = :iddisciplina, "
								+ "idusuario = :idusuario "
								+ "WHERE idjuez = :idjuez", nativeQuery = true)
	Integer actualizarJuez(
						@Param("idjuez") Integer idjuez,
						@Param("nombre") String nombre,
						@Param("apellidop") String apellidop,
						@Param("apellidom") String apellidom,
						@Param("idusuario") Integer idusuario, 
						@Param("iddisciplina") Integer iddisciplina);
	
	@Modifying
	@Transactional
    @Query(value="DELETE FROM Juez WHERE idjuez = :idjuez", nativeQuery = true)
    void eliminarJuez(@Param("idjuez") Integer idjuez);

	@Query(value = "SELECT * FROM Juez WHERE idusuario = :idusuario", nativeQuery = true)
	Juez findByIdusuario(@Param("idusuario") Integer idusuario);
}
