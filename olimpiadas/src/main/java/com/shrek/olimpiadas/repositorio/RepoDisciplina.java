package com.shrek.olimpiadas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shrek.olimpiadas.modelo.Disciplina;

public interface RepoDisciplina  extends JpaRepository<Disciplina, Integer>{

	@Query(value = "SELECT * FROM Disciplina WHERE iddisciplina = :iddisciplina", nativeQuery = true)
	Disciplina findByIddisciplina(@Param("iddisciplina") String iddisciplina);
	
}
