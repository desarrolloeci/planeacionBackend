package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.FactorFin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorFinRepository extends JpaRepository<FactorFin, Integer>, JpaSpecificationExecutor<FactorFin> {
}
