package com.shrek.olimpiadas.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shrek.olimpiadas.modelo.Competidor;

@Repository
public interface RepoCompetidor extends JpaRepository<Competidor, String>{
	
	@Query(value = "SELECT * FROM Competidor WHERE idcompetidor = :idcompetidor", nativeQuery = true)
	Competidor findByIdcompetidor(@Param("idcompetidor") String idcompetidor);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Competidor (idcompetidor, nombre, apellidop, apellidom, sexo, nacimiento, escuela, idusuario, iddisciplina) "
				 + "VALUES (:idcompetidor, :nombre, :apellidop, :apellidom, :sexo, :nacimiento, :escuela, :idusuario, :iddisciplina)", nativeQuery = true)
	void agregarCompetidor(	@Param("idcompetidor") String idcompetidor, 
						 	@Param("nombre") String nombre,
						 	@Param("apellidop") String apellidop,
						 	@Param("apellidom") String apellidom,
						 	@Param("sexo") Integer sexo,
						 	@Param("nacimiento") String nacimiento,
						 	@Param("escuela") String escuela,
						 	@Param("idusuario") Integer idusuario,
							@Param("iddisciplina") Integer iddisplina);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Competidor SET idcompetidor = :idcompetidor, "
									   + "nombre = :nombre, "
								 	   + "apellidop = :apellidop, "
								 	   + "apellidom = :apellidom, "
								 	   + "sexo = :sexo, "
								 	   + "nacimiento = :nacimiento, "
								 	   + "escuela = :escuela, "
								 	   + "idusuario = :idusuario, "
			                           + "iddisciplina = :iddisciplina "
								 	   + "WHERE idcompetidor = :idcompetidor", nativeQuery = true)
	Integer actualizarCompetidor(@Param("idcompetidor") String idcompetidor,
								 @Param("nombre") String nombre,
								 @Param("apellidop") String apellidop,
								 @Param("apellidom") String apellidom,
								 @Param("sexo") Integer sexo,
								 @Param("nacimiento") String nacimiento,
								 @Param("escuela") String escuela,
								 @Param("idusuario") Integer idusuario,
								 @Param("iddisciplina") Integer iddisplina);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Competidor WHERE idcompetidor = :idcompetidor", nativeQuery = true)
    void eliminarCompetidor(@Param("idcompetidor") String idcompetidor);

}
