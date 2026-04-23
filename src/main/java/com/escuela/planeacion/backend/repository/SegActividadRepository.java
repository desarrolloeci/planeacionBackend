package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.SegActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegActividadRepository extends JpaRepository<SegActividad, Integer> {

    List<SegActividad> findByIdseguimiento(Integer idseguimiento);

    List<SegActividad> findByIdactividad(Integer idactividad);
}
