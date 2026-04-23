package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Proyecto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    // ProyectoRepository.java
List<Proyecto> findByEstadoprIn(List<Integer> estados);
}
