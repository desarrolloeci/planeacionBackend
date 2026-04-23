package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
    List<Parametro> findByTipo(String tipo);
}
