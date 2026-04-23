package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.EjePrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjeProgramaRepository extends JpaRepository<EjePrograma, Integer> {
}
