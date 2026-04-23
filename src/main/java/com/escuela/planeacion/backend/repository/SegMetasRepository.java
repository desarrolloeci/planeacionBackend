package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.SegMetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegMetasRepository extends JpaRepository<SegMetas, Integer> {
    List<SegMetas> findByIdseguimeinto(Integer idseguimeinto);
}
