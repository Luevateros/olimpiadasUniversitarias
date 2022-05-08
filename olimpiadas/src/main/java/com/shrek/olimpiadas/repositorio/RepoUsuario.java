package com.shrek.olimpiadas.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shrek.olimpiadas.modelo.Usuario;

@Repository
public interface RepoUsuario  extends JpaRepository<Usuario, Integer>{

	@Query(value = "SELECT * FROM Usuario WHERE correo = :correo", nativeQuery = true)
	Usuario findByCorreo(@Param("correo") String correo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario SET correo = :correo, "
									+ "password = :password, "
									+ "tipousuario = :tipousuario "
									+ "WHERE idusuario = :idusuario", nativeQuery = true)
	Integer actualizarUsuario(@Param("correo") String correo, 
							  @Param("password") String password,
							  @Param("tipousuario") String tipousuario,
							  @Param("idusuario") Integer idusuario);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Usuario WHERE idusuario = :idusuario", nativeQuery = true)
    void eliminarUsuario(@Param("idusuario") Integer idusuario);
}
