package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Seguimiento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
	List<Seguimiento> findByIdproyecto(Integer idproyecto);
}
