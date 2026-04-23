package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.dto.CargosDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargosService {

    private final JdbcTemplate jdbcTemplate;

    public CargosService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CargosDTO> getCargos() {
        String sql = """
            SELECT a.cod_car, a.nom_car, b.usr_val_hora
            FROM Novasoft.dbo.rhh_cargos a
            LEFT JOIN Novasoft.dbo.usr_rhh_cargos b ON a.cod_car = b.cod_car
            WHERE a.cod_car IN (
                SELECT DISTINCT cod_car
                FROM Novasoft.dbo.rhh_emplea
                WHERE est_lab IN ('01','02')
            )
            ORDER BY a.nom_car ASC
            """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new CargosDTO(
                rs.getString("cod_car"),
                rs.getString("nom_car"),
                rs.getString("usr_val_hora")
            )
        );
    }
}
