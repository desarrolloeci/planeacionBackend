package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.EjeProgramaProyecto;
import com.escuela.planeacion.backend.entity.EjeProgramaProyectoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjeProgramaProyectoRepository
        extends JpaRepository<EjeProgramaProyecto, EjeProgramaProyectoId> {

    List<EjeProgramaProyecto> findByIdproyecto(Integer idproyecto);
}
