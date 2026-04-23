package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.EjeProgramaProyecto;
import com.escuela.planeacion.backend.entity.EjeProgramaProyectoId;
import com.escuela.planeacion.backend.repository.EjeProgramaProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


@Service
public class EjeProgramaProyectoService {

    private final EjeProgramaProyectoRepository repository;

    public EjeProgramaProyectoService(EjeProgramaProyectoRepository repository) {
        this.repository = repository;
    }

    // =========================
    // GET
    // =========================

    public List<EjeProgramaProyecto> getAll() {
        return repository.findAll();
    }

    public Optional<EjeProgramaProyecto> getById(
            Integer idproyecto,
            Integer idejeprograma
    ) {
        return repository.findById(
                new EjeProgramaProyectoId(idproyecto, idejeprograma)
        );
    }

    public List<EjeProgramaProyecto> getByIdProyecto(Integer idproyecto) {
        return repository.findByIdproyecto(idproyecto);
    }

    // =========================
    // SAVE
    // =========================

    public EjeProgramaProyecto save(EjeProgramaProyecto entity) {
        return repository.save(entity);
    }

    // =========================
    // UPDATE
    // =========================

    public EjeProgramaProyecto update(
            Integer idproyecto,
            Integer idejeprograma,
            EjeProgramaProyecto entity
    ) {
        entity.setIdproyecto(idproyecto);
        entity.setIdejeprograma(idejeprograma);
        return repository.save(entity);
    }

    // =========================
    // DELETE
    // =========================

    public void delete(
            Integer idproyecto,
            Integer idejeprograma
    ) {
        repository.deleteById(
                new EjeProgramaProyectoId(idproyecto, idejeprograma)
        );
    }
    
    @Transactional
    public void marcarEjePrincipal(Integer idproyecto, Integer idejeprograma) {

        List<EjeProgramaProyecto> ejes = repository.findByIdproyecto(idproyecto);

        for (EjeProgramaProyecto eje : ejes) {
            eje.setEjeppal(
                eje.getIdejeprograma().equals(idejeprograma) ? "1" : "0"
            );
        }

        repository.saveAll(ejes);
    }
}
