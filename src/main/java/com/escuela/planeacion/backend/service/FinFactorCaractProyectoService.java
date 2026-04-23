package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.FinFactorCaractProyecto;
import com.escuela.planeacion.backend.repository.FinFactorCaractProyectoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinFactorCaractProyectoService {

    private final FinFactorCaractProyectoRepository repository;

    public FinFactorCaractProyectoService(FinFactorCaractProyectoRepository repository) {
        this.repository = repository;
    }

    public List<FinFactorCaractProyecto> getAll() {
        return repository.findAll();
    }

    public Optional<FinFactorCaractProyecto> getById(Integer id) {
        return repository.findById(id);
    }

    public FinFactorCaractProyecto save(FinFactorCaractProyecto entity) {
        return repository.save(entity);
    }



    public FinFactorCaractProyecto update(Integer id, FinFactorCaractProyecto entity) {
        entity.setIdproyecto(id);
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public FinFactorCaractProyecto insert(FinFactorCaractProyecto entity) {
        repository.insertFinFactorCaractProyecto(
            entity.getIdproyecto(),
            entity.getIdfin(),
            entity.getIdfactor(),
            entity.getNombrecaract(),
            entity.getIdfactintegral(),
            entity.getEje()
        );
        return entity;
    }

    // Método adicional
    public List<FinFactorCaractProyecto> getByIdProyecto(Integer idproyecto) {
        return repository.findByIdproyecto(idproyecto);
    }
}
