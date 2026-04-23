package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.DedicacionSemanal;
import com.escuela.planeacion.backend.repository.DedicacionSemanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DedicacionSemanalService {

    @Autowired
    private DedicacionSemanalRepository repository;

    // Crear nueva dedicación
    public DedicacionSemanal crear(DedicacionSemanal dedicacion) {
        dedicacion.setCreatedAt(LocalDateTime.now());
        dedicacion.setUpdatedAt(LocalDateTime.now());
        return repository.save(dedicacion);
    }

    // Consultar todas o por filtros
    public List<DedicacionSemanal> listar() {
        return repository.findAll();
    }

    public List<DedicacionSemanal> listarPorActividad(Integer idactividad) {
        return repository.findByIdactividad(idactividad);
    }

    public List<DedicacionSemanal> listarPorPersonal(Integer idpersonal) {
        return repository.findByIdpersonal(idpersonal);
    }

    public List<DedicacionSemanal> listarPorCargo(String idcargo) {
        return repository.findByIdcargo(idcargo);
    }

    // Buscar por ID
    public Optional<DedicacionSemanal> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    // Actualizar dedicación existente
    public DedicacionSemanal actualizar(Integer id, DedicacionSemanal nuevaDedicacion) {
        return repository.findById(id).map(existing -> {
            existing.setHorasPorSemana(nuevaDedicacion.getHorasPorSemana());
            existing.setSemanasCalculadas(nuevaDedicacion.getSemanasCalculadas());
            existing.setHorasTotales(nuevaDedicacion.getHorasTotales());
            existing.setHorasTotales(nuevaDedicacion.getHorasTotales());
            existing.setFechaInicio(nuevaDedicacion.getFechaInicio());
            existing.setFechaFin(nuevaDedicacion.getFechaFin());
            existing.setUpdatedBy(nuevaDedicacion.getUpdatedBy());
            existing.setUpdatedAt(LocalDateTime.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Dedicación no encontrada con ID: " + id));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<DedicacionSemanal> listarPorActividadYPersonal(Integer idactividad, Integer idpersonal) {
    return repository.findByIdactividadAndIdpersonal(idactividad, idpersonal);
}

public List<DedicacionSemanal> listarPorActividadYCargo(Integer idactividad, String idcargo) {
    return repository.findByIdactividadAndIdcargo(idactividad, idcargo);
}
}
