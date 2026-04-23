package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.SegIndicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegIndicadorRepository extends JpaRepository<SegIndicador, Integer> {
    List<SegIndicador> findByIdseguimiento(Integer idseguimiento);
}
