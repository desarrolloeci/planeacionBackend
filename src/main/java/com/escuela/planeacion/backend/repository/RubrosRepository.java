package com.escuela.planeacion.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.escuela.planeacion.backend.entity.Rubros;

@Repository
public interface RubrosRepository extends JpaRepository<Rubros, String> {
	@Query("SELECT r FROM Rubros r WHERE r.cod_cl1 = :codCl1 AND r.ano_acu = YEAR(CURRENT_DATE)")
    List<Rubros> buscarPorCodCl1(@Param("codCl1") String codCl1);
}