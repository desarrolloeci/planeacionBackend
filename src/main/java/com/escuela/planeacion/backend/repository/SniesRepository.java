package com.escuela.planeacion.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escuela.planeacion.backend.entity.Snies;

@Repository
public interface SniesRepository extends JpaRepository<Snies, String> {
}
