package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.ObjetivoEje;
import com.escuela.planeacion.backend.entity.ObjetivoEjeId;
import com.escuela.planeacion.backend.repository.ObjetivoEjeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetivoEjeService {

    private final ObjetivoEjeRepository objetivoEjeRepository;

    public ObjetivoEjeService(ObjetivoEjeRepository objetivoEjeRepository) {
        this.objetivoEjeRepository = objetivoEjeRepository;
    }

    public List<ObjetivoEje> listarTodos() {
        return objetivoEjeRepository.findAll();
    }

    public Optional<ObjetivoEje> obtenerPorId(ObjetivoEjeId id) {
        return objetivoEjeRepository.findById(id);
    }

    public List<ObjetivoEje> listarPorEjePrograma(Integer idEjePrograma) {
        return objetivoEjeRepository.findByIdIdEjePrograma(idEjePrograma);
    }

    public ObjetivoEje crear(ObjetivoEje objetivoEje) {
        return objetivoEjeRepository.save(objetivoEje);
    }

    public ObjetivoEje actualizar(ObjetivoEjeId id, ObjetivoEje objetivoEje) {
        return objetivoEjeRepository.findById(id)
                .map(existing -> {
                    objetivoEje.setId(id);
                    return objetivoEjeRepository.save(objetivoEje);
                })
                .orElseThrow(() -> new RuntimeException("Objetivo EJE no encontrado"));
    }

    public void eliminar(ObjetivoEjeId id) {
        objetivoEjeRepository.deleteById(id);
    }
}
