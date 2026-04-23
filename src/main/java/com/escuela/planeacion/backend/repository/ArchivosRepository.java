package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Archivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivosRepository extends JpaRepository<Archivos, Integer> {
    List<Archivos> findByIdactividad(Integer idactividad);
    List<Archivos> findByIdproyecto(Integer idproyecto);
}
