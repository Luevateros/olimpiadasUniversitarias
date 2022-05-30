package com.shrek.olimpiadas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shrek.olimpiadas.modelo.Disciplina;

import javax.transaction.Transactional;
import java.util.List;

public interface RepoDisciplina  extends JpaRepository<Disciplina, Integer>{

	@Query(value = "SELECT * FROM Disciplina", nativeQuery = true)
	List<Disciplina> findAll();

	@Query(value = "SELECT * FROM Disciplina WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	Disciplina findByIddisciplina(@Param("iddisciplina") Integer iddisciplina);


	@Query(value = "SELECT * FROM Disciplina WHERE nombre = :nombre", nativeQuery = true)
	Disciplina findByNombre(@Param("nombre") Integer nombre);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Disciplina (nombre, responsable, imagen, descripcion) "
			+ "VALUES (:nombre, :responsable, :imagen, :descripcion)", nativeQuery = true)
	void agregarDisciplina(@Param("nombre") String nombre,
						   @Param("responsable") String responsable,
						   @Param("imagen") String imagen,
						   @Param("descripcion") String descripcion);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Disciplina SET nombre = :nombre ,"
			+ "responsable = :responsable ,"
			+ "imagen = :imagen ,"
			+ "descripcion = :descripcion "
			+ "WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	Integer actualizarDisciplina(@Param("nombre") String nombre,
								 @Param("responsable") String responsable,
								 @Param("imagen") String imagen,
								 @Param("descripcion") String descripcion,
								 @Param("iddisciplina") Integer iddisciplina);

	@Query(value = "SELECT COUNT(idcompetidor) FROM Competidor  WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	Integer competidores(@Param("iddisciplina") Integer iddisciplina);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Disciplina WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	void eliminarDisciplina(@Param("iddisciplina") Integer iddisciplina);
}
