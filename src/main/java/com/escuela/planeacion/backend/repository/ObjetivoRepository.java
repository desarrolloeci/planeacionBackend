package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
    List<Objetivo> findByIdproyecto(Integer idproyecto);
}
