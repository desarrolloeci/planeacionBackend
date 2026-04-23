package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, String> {

}
