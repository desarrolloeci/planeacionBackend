package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Actividad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
	List<Actividad> findByIdproyecto(Integer idproyecto);
}
