package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Indicador;
import com.escuela.planeacion.backend.repository.IndicadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicadorService {

    private final IndicadorRepository repository;

    public IndicadorService(IndicadorRepository repository) {
        this.repository = repository;
    }

    public List<Indicador> getAll() {
        return repository.findAll();
    }

    public Optional<Indicador> getById(Integer id) {
        return repository.findById(id);
    }

    public Indicador save(Indicador indicador) {
        return repository.save(indicador);
    }

    public Indicador update(Integer id, Indicador indicador) {
        indicador.setIdindicador(id);
        return repository.save(indicador);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // Métodos adicionales
    public List<Indicador> getByIdProyecto(Integer idproyecto) {
        return repository.findByIdproyecto(idproyecto);
    }

    public List<Indicador> getByIdActividad(Integer idactividad) {
        return repository.findByIdactividad(idactividad);
    }
}
