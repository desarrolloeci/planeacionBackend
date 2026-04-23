package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.SegMetas;
import com.escuela.planeacion.backend.repository.SegMetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegMetasService {

    @Autowired
    private SegMetasRepository segMetasRepository;

    public List<SegMetas> getAll() {
        return segMetasRepository.findAll();
    }

    public SegMetas getById(Integer id) {
        return segMetasRepository.findById(id).orElse(null);
    }

    public List<SegMetas> getByIdSeguimiento(Integer idseguimeinto) {
        return segMetasRepository.findByIdseguimeinto(idseguimeinto);
    }

    public SegMetas save(SegMetas segMetas) {
        return segMetasRepository.save(segMetas);
    }

    public SegMetas update(Integer id, SegMetas segMetas) {
        SegMetas existente = segMetasRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setIdmetaobj(segMetas.getIdmetaobj());
            existente.setIdseguimeinto(segMetas.getIdseguimeinto());
            existente.setDescripavancemetobj(segMetas.getDescripavancemetobj());
            return segMetasRepository.save(existente);
        }
        return null;
    }

    public void delete(Integer id) {
        segMetasRepository.deleteById(id);
    }
}
