package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.UnidadEjecutora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadEjecutoraRepository extends JpaRepository<UnidadEjecutora, Integer> {
}
