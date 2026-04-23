package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Seguimiento;
import com.escuela.planeacion.backend.repository.SeguimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // GET - todos los seguimientos
    public List<Seguimiento> getAll() {
        return seguimientoRepository.findAll();
    }

    // GET - uno por ID
    public Seguimiento getById(Integer id) {
        return seguimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado con id " + id));
    }

    // POST - llamar procedimiento almacenado
    public void crearSeguimiento(String proy) {
        jdbcTemplate.update("EXEC Planeacion.odi.SeguimientoODI ?", proy);
    }

    // PUT - actualizar registro directamente
    public Seguimiento actualizar(Integer id, Seguimiento datos) {
        return seguimientoRepository.findById(id)
                .map(seg -> {
                    seg.setFechaseg(datos.getFechaseg());
                    seg.setPrcntavanceproyseg(datos.getPrcntavanceproyseg());
                    seg.setEstadoproyseg(datos.getEstadoproyseg());
                    seg.setEstadosistemaseg(datos.getEstadosistemaseg());
                    seg.setDescripavanceseg(datos.getDescripavanceseg());
                    seg.setAccionesseg(datos.getAccionesseg());
                    seg.setIdproyecto(datos.getIdproyecto());
                    seg.setEstadoseg(datos.getEstadoseg());
                    seg.setDificultadesavance(datos.getDificultadesavance());
                    seg.setFechaenvioseg(datos.getFechaenvioseg());
                    return seguimientoRepository.save(seg);
                })
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado con id " + id));
    }

    // DELETE - eliminar seguimiento
    public void eliminar(Integer id) {
        if (!seguimientoRepository.existsById(id)) {
            throw new RuntimeException("Seguimiento no encontrado con id " + id);
        }
        seguimientoRepository.deleteById(id);
    }
    public List<Seguimiento> getByIdProyecto(Integer idproyecto) {
        return seguimientoRepository.findByIdproyecto(idproyecto);
    }
}
