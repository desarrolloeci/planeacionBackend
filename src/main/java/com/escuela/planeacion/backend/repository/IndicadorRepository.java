package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Integer> {
    List<Indicador> findByIdproyecto(Integer idproyecto);
    List<Indicador> findByIdactividad(Integer idactividad);
}
