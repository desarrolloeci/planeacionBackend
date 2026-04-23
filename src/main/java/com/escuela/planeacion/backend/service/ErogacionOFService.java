package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.ErogacionOF;
import com.escuela.planeacion.backend.repository.ErogacionOFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErogacionOFService {

    @Autowired
    private ErogacionOFRepository repository;

    public List<ErogacionOF> getTodasErogaciones() {
        return repository.findAll();
    }

    public ErogacionOF getErogacionPorId(Integer iderogacionof) {
        return repository.findById(iderogacionof)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<ErogacionOF> getErogacionesPorIdproyecto(Integer idproyecto) {
        return repository.findByIdproyecto(idproyecto);
    }

    public ErogacionOF crearErogacion(ErogacionOF erogacion) {
        return repository.save(erogacion);
    }

    public ErogacionOF actualizarErogacion(Integer iderogacionof, ErogacionOF erogacionActualizado) {
        ErogacionOF erogacion = repository.findById(iderogacionof)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        erogacion.setCcosto(erogacionActualizado.getCcosto());
        erogacion.setRubro(erogacionActualizado.getRubro());
        erogacion.setTiporub(erogacionActualizado.getTiporub());
        erogacion.setIdproyecto(erogacionActualizado.getIdproyecto());
        erogacion.setValor(erogacionActualizado.getValor());
        erogacion.setFecharub(erogacionActualizado.getFecharub());

        return repository.save(erogacion);
    }

    public void eliminarErogacion(Integer iderogacionof) {
        ErogacionOF erogacion = repository.findById(iderogacionof)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(erogacion);
    }
}
