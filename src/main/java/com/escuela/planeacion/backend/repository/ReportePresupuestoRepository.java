package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.dto.ReportePresupuestoDTO;
import com.escuela.planeacion.backend.entity.Proyecto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ReportePresupuestoRepository extends CrudRepository<Proyecto, Integer> {

	@Query(value = """
		    SELECT 
		        x.idactividad,
		        x.nombreact AS actividad,
		        x.agno AS anio,
		        SUM(x.dedicacionPersonal) AS dedicacionPersonal,
		        SUM(x.presupuestoErogacion) AS presupuestoErogacion,
		        SUM(x.dedicacionPersonal + x.presupuestoErogacion) AS totalActividad
		    FROM (
		        SELECT 
		            a.idactividad,
		            a.nombreact,
		            h.agno,
		            SUM(h.horas * CAST(h.CantPer AS INT) * p.valorprs) AS dedicacionPersonal,
		            0 AS presupuestoErogacion
		        FROM Planeacion.odi.Actividad a
		        INNER JOIN Planeacion.odi.Personal p ON a.idactividad = p.idactividad
		        INNER JOIN Planeacion.odi.Horas_Personal h ON p.idpersonal = h.idpersonal
		        WHERE a.idproyecto = :idProyecto
		        GROUP BY a.idactividad, a.nombreact, h.agno

		        UNION ALL

		        SELECT 
		            a.idactividad,
		            a.nombreact,
		            e.agno,
		            0 AS dedicacionPersonal,
		            SUM(e.valor) AS presupuestoErogacion
		        FROM Planeacion.odi.Actividad a
		        INNER JOIN Planeacion.odi.ErogacionPL e ON a.idactividad = e.idactividad
		        WHERE a.idproyecto = :idProyecto
		        GROUP BY a.idactividad, a.nombreact, e.agno
		    ) x
		    GROUP BY x.idactividad, x.nombreact, x.agno
		    ORDER BY x.nombreact, x.agno
		""", nativeQuery = true)
		List<Object[]> obtenerReportePresupuestoRaw(@Param("idProyecto") Integer idProyecto);
}
