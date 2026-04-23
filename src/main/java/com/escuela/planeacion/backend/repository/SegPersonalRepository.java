package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.SegPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegPersonalRepository extends JpaRepository<SegPersonal, Integer> {

    List<SegPersonal> findByIdseguimiento(Integer idseguimiento);

    List<SegPersonal> findByIdactividad(Integer idactividad);

    List<SegPersonal> findByIdpersonal(Integer idpersonal);
}
