package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.HorasPersonal;
import com.escuela.planeacion.backend.entity.HorasPersonalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasPersonalRepository extends JpaRepository<HorasPersonal, HorasPersonalId> {

    List<HorasPersonal> findById_IdPersonal(Integer idPersonal);
}
