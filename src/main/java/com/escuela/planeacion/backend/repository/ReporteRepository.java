package com.escuela.planeacion.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.escuela.planeacion.backend.dto.ProyectoReporteDTO;
import com.escuela.planeacion.backend.entity.Proyecto;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Proyecto, Long> {

    @Query(value = """
        SELECT 
            p.idproyecto AS idProyecto,
            p.nombrepr AS nombreProyecto,
            p.estadopr AS estado,
            CONCAT(CONVERT(varchar, p.fechainipr, 23), ' - ', CONVERT(varchar, p.fechafinpr, 23)) AS vigencia,
            ue.nombreunidad AS unidadEjecutora,
            o.descripcionob AS objetivo,
            a.nombreact AS actividad,
            p.metapr AS meta,
            i.nombreind AS indicador
        FROM [Planeacion].[odi].[Proyecto] p
        LEFT JOIN [Planeacion].[odi].[UnidadEjecutora] ue ON ue.idunidadej = p.unidadejecutora
        LEFT JOIN [Planeacion].[odi].[Objetivo] o ON o.idproyecto = p.idproyecto
        LEFT JOIN [Planeacion].[odi].[Actividad] a ON a.idproyecto = p.idproyecto
        LEFT JOIN [Planeacion].[odi].[Indicador] i ON i.idproyecto = p.idproyecto
        ORDER BY p.idproyecto
        """, nativeQuery = true)
    List<ProyectoReporteDTO> obtenerReporteProyectos();
}