package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.RubroPlaneacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroPlaneacionRepository extends JpaRepository<RubroPlaneacion, Integer> {
}
