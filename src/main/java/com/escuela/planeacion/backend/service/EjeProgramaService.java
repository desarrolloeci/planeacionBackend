package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.EjePrograma;
import com.escuela.planeacion.backend.repository.EjeProgramaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjeProgramaService {

    private final EjeProgramaRepository ejeProgramaRepository;

    public EjeProgramaService(EjeProgramaRepository ejeProgramaRepository) {
        this.ejeProgramaRepository = ejeProgramaRepository;
    }

    public List<EjePrograma> listarTodos() {
        return ejeProgramaRepository.findAll();
    }

    public Optional<EjePrograma> obtenerPorId(Integer id) {
        return ejeProgramaRepository.findById(id);
    }

    public EjePrograma crear(EjePrograma ejePrograma) {
        return ejeProgramaRepository.save(ejePrograma);
    }

    public EjePrograma actualizar(Integer id, EjePrograma ejePrograma) {
        return ejeProgramaRepository.findById(id)
                .map(existing -> {
                    ejePrograma.setIdejeprograma(id);
                    return ejeProgramaRepository.save(ejePrograma);
                })
                .orElseThrow(() -> new RuntimeException("EjePrograma no encontrado"));
    }

    public void eliminar(Integer id) {
        ejeProgramaRepository.deleteById(id);
    }
}
