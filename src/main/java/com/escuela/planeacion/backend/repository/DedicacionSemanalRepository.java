package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.DedicacionSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DedicacionSemanalRepository extends JpaRepository<DedicacionSemanal, Integer> {

    List<DedicacionSemanal> findByIdactividad(Integer idactividad);

    List<DedicacionSemanal> findByIdpersonal(Integer idpersonal);

    List<DedicacionSemanal> findByIdcargo(String idcargo);

    List<DedicacionSemanal> findByIdactividadAndIdpersonal(Integer idactividad, Integer idpersonal);
    List<DedicacionSemanal> findByIdactividadAndIdcargo(Integer idactividad, String idcargo);

}
