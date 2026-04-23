package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.ErogacionPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErogacionPLRepository extends JpaRepository<ErogacionPL, Integer> {

    List<ErogacionPL> findByIdproyecto(Integer idproyecto);

    List<ErogacionPL> findByIdactividad(Integer idactividad);
}
