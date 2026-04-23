package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.RubroPlaneacion;
import com.escuela.planeacion.backend.repository.RubroPlaneacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RubroPlaneacionService {

    private final RubroPlaneacionRepository rubroPlaneacionRepository;

    public RubroPlaneacionService(RubroPlaneacionRepository rubroPlaneacionRepository) {
        this.rubroPlaneacionRepository = rubroPlaneacionRepository;
    }

    public List<RubroPlaneacion> listarTodos() {
        return rubroPlaneacionRepository.findAll();
    }

    public Optional<RubroPlaneacion> obtenerPorId(Integer id) {
        return rubroPlaneacionRepository.findById(id);
    }

    public RubroPlaneacion crear(RubroPlaneacion rubroPlaneacion) {
        return rubroPlaneacionRepository.save(rubroPlaneacion);
    }

    public RubroPlaneacion actualizar(Integer id, RubroPlaneacion rubroPlaneacion) {
        return rubroPlaneacionRepository.findById(id)
                .map(existing -> {
                    rubroPlaneacion.setIdrubropl(id);
                    return rubroPlaneacionRepository.save(rubroPlaneacion);
                })
                .orElseThrow(() -> new RuntimeException("Rubro de planeación no encontrado"));
    }

    public void eliminar(Integer id) {
        rubroPlaneacionRepository.deleteById(id);
    }
}
