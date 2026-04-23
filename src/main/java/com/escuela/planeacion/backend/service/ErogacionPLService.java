package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.ErogacionPL;
import com.escuela.planeacion.backend.repository.ErogacionPLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErogacionPLService {

    @Autowired
    private ErogacionPLRepository repository;

    public List<ErogacionPL> getTodasErogaciones() {
        return repository.findAll();
    }

    public ErogacionPL getErogacionPorId(Integer iderogacionpl) {
        return repository.findById(iderogacionpl)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<ErogacionPL> getErogacionesPorIdproyecto(Integer idproyecto) {
        return repository.findByIdproyecto(idproyecto);
    }

    public List<ErogacionPL> getErogacionesPorIdactividad(Integer idactividad) {
        return repository.findByIdactividad(idactividad);
    }

    public ErogacionPL crearErogacion(ErogacionPL erogacion) {
        return repository.save(erogacion);
    }

    public ErogacionPL actualizarErogacion(Integer iderogacionpl, ErogacionPL erogacionActualizado) {
        ErogacionPL erogacion = repository.findById(iderogacionpl)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        erogacion.setTiporubpl(erogacionActualizado.getTiporubpl());
        erogacion.setRubropl(erogacionActualizado.getRubropl());
        erogacion.setObservacionpl(erogacionActualizado.getObservacionpl());
        erogacion.setIdproyecto(erogacionActualizado.getIdproyecto());
        erogacion.setIdactividad(erogacionActualizado.getIdactividad());
        erogacion.setValor(erogacionActualizado.getValor());
        erogacion.setAgno(erogacionActualizado.getAgno());

        return repository.save(erogacion);
    }

    public void eliminarErogacion(Integer iderogacionpl) {
        ErogacionPL erogacion = repository.findById(iderogacionpl)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(erogacion);
    }
}
