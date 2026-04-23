package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    // Método para filtrar por idactividad
    List<Personal> findByIdactividad(Integer idActividad);
}
