package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.FinFactorCaractProyecto;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinFactorCaractProyectoRepository extends JpaRepository<FinFactorCaractProyecto, Integer> {

    List<FinFactorCaractProyecto> findByIdproyecto(Integer idproyecto);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO [Planeacion].[odi].[FinFactorCaractProyecto]
        (idproyecto, idfin, idfactor, nombrecaract, idfactintegral, eje)
        VALUES (:idproyecto, :idfin, :idfactor, :nombrecaract, :idfactintegral, :eje)
    """, nativeQuery = true)
    void insertFinFactorCaractProyecto(
        @Param("idproyecto") Integer idproyecto,
        @Param("idfin") Integer idfin,
        @Param("idfactor") Integer idfactor,
        @Param("nombrecaract") String nombrecaract,
        @Param("idfactintegral") Integer idfactintegral,
        @Param("eje") String eje
    );
}
