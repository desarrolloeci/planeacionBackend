package com.escuela.planeacion.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.escuela.planeacion.backend.dto.CentroCostoDTO;

import java.util.List;

@Service
public class CentroCostoService {

    private final JdbcTemplate jdbcTemplate;

    public CentroCostoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CentroCostoDTO> getCentroCostosActuales() {
        String sql = """
            SELECT DISTINCT cod_cl1, 
                   (SELECT nombre 
                    FROM Novasoft.dbo.gen_clasif1 
                    WHERE RP.cod_cl1 = codigo) AS NCcosto
            FROM Novasoft.dbo.usr_rubros_planeacion RP
            WHERE ano_acu = YEAR(GETDATE())
              AND cod_cl1 <> '0'
            ORDER BY NCcosto ASC
            """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new CentroCostoDTO(
                rs.getString("cod_cl1"),
                rs.getString("NCcosto")
            )
        );
    }
}