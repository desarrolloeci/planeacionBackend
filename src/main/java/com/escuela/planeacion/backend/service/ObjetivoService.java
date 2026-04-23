package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Objetivo;
import com.escuela.planeacion.backend.repository.ObjetivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetivoService {

    private final ObjetivoRepository objetivoRepository;

    public ObjetivoService(ObjetivoRepository objetivoRepository) {
        this.objetivoRepository = objetivoRepository;
    }

    public List<Objetivo> listarTodos() {
        return objetivoRepository.findAll();
    }

    public Optional<Objetivo> obtenerPorId(Integer id) {
        return objetivoRepository.findById(id);
    }

    public List<Objetivo> listarPorProyecto(Integer idproyecto) {
        return objetivoRepository.findByIdproyecto(idproyecto);
    }

    public Objetivo crear(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }

    public Objetivo actualizar(Integer id, Objetivo objetivo) {
        return objetivoRepository.findById(id)
                .map(existing -> {
                    objetivo.setIdobjetivo(id);
                    return objetivoRepository.save(objetivo);
                })
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado"));
    }

    public void eliminar(Integer id) {
        objetivoRepository.deleteById(id);
    }
}
