package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.SegErogacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegErogacionRepository extends JpaRepository<SegErogacion, Integer> {

    List<SegErogacion> findByIdseguimiento(Integer idseguimiento);

    List<SegErogacion> findByAgno(Integer agno);
}
