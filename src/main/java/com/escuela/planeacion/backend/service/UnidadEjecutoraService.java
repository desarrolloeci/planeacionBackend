package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.UnidadEjecutora;
import com.escuela.planeacion.backend.repository.UnidadEjecutoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadEjecutoraService {

    private final UnidadEjecutoraRepository unidadEjecutoraRepository;

    public UnidadEjecutoraService(UnidadEjecutoraRepository unidadEjecutoraRepository) {
        this.unidadEjecutoraRepository = unidadEjecutoraRepository;
    }

    public List<UnidadEjecutora> listarTodos() {
        return unidadEjecutoraRepository.findAll();
    }

    public Optional<UnidadEjecutora> obtenerPorId(Integer id) {
        return unidadEjecutoraRepository.findById(id);
    }

    public UnidadEjecutora crear(UnidadEjecutora unidadEjecutora) {
        return unidadEjecutoraRepository.save(unidadEjecutora);
    }

    public UnidadEjecutora actualizar(Integer id, UnidadEjecutora unidadEjecutora) {
        return unidadEjecutoraRepository.findById(id)
                .map(existing -> {
                    unidadEjecutora.setIdunidadej(id);
                    return unidadEjecutoraRepository.save(unidadEjecutora);
                })
                .orElseThrow(() -> new RuntimeException("Unidad ejecutora no encontrada"));
    }

    public void eliminar(Integer id) {
        unidadEjecutoraRepository.deleteById(id);
    }
}
