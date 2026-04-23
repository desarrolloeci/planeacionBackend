package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.ObjetivoEje;
import com.escuela.planeacion.backend.entity.ObjetivoEjeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoEjeRepository extends JpaRepository<ObjetivoEje, ObjetivoEjeId> {
    List<ObjetivoEje> findByIdIdEjePrograma(Integer idEjePrograma);
}