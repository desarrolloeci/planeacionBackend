package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.FechaSeguimiento;

import jakarta.transaction.Transactional;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaSeguimientoRepository extends JpaRepository<FechaSeguimiento, Date> {
	
	@Modifying
    @Transactional
    @Query(
        value = "UPDATE Planeacion.odi.FechaSeguimiento SET flag = :nuevoFlag WHERE feciniseg = :feciniseg",
        nativeQuery = true
    )
    int actualizarFlag(@Param("feciniseg") String feciniseg, @Param("nuevoFlag") Integer nuevoFlag);
}
