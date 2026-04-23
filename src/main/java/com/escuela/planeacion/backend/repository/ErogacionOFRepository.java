package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.ErogacionOF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErogacionOFRepository extends JpaRepository<ErogacionOF, Integer> {

    List<ErogacionOF> findByIdproyecto(Integer idproyecto);
}
