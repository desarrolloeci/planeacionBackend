package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.SegIndicador;
import com.escuela.planeacion.backend.repository.SegIndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegIndicadorService {

    @Autowired
    private SegIndicadorRepository segIndicadorRepository;

    public List<SegIndicador> getAll() {
        return segIndicadorRepository.findAll();
    }

    public SegIndicador getById(Integer id) {
        return segIndicadorRepository.findById(id).orElse(null);
    }

    public List<SegIndicador> getByIdSeguimiento(Integer idseguimiento) {
        return segIndicadorRepository.findByIdseguimiento(idseguimiento);
    }

    public SegIndicador save(SegIndicador segIndicador) {
        return segIndicadorRepository.save(segIndicador);
    }

    public SegIndicador update(Integer id, SegIndicador segIndicador) {
        SegIndicador existente = segIndicadorRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setResultado(segIndicador.getResultado());
            existente.setDescripresult(segIndicador.getDescripresult());
            existente.setIdindicador(segIndicador.getIdindicador());
            existente.setIdseguimiento(segIndicador.getIdseguimiento());
            return segIndicadorRepository.save(existente);
        }
        return null;
    }

    public void delete(Integer id) {
        segIndicadorRepository.deleteById(id);
    }
}
