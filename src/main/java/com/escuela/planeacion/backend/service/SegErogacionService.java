package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.SegErogacion;
import com.escuela.planeacion.backend.repository.SegErogacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegErogacionService {

    @Autowired
    private SegErogacionRepository repository;

    public List<SegErogacion> getTodasErogaciones() {
        return repository.findAll();
    }

    public SegErogacion getErogacionPorId(Integer iderogacionseg) {
        return repository.findById(iderogacionseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<SegErogacion> getErogacionesPorIdseguimiento(Integer idseguimiento) {
        return repository.findByIdseguimiento(idseguimiento);
    }

    public List<SegErogacion> getErogacionesPorAgno(Integer agno) {
        return repository.findByAgno(agno);
    }

    public SegErogacion crearErogacion(SegErogacion erogacion) {
        return repository.save(erogacion);
    }

    public SegErogacion actualizarErogacion(Integer iderogacionseg, SegErogacion erogacionActualizado) {
        SegErogacion erogacion = repository.findById(iderogacionseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        erogacion.setAgno(erogacionActualizado.getAgno());
        erogacion.setSaldo(erogacionActualizado.getSaldo());
        erogacion.setIdseguimiento(erogacionActualizado.getIdseguimiento());
        erogacion.setApropiacion(erogacionActualizado.getApropiacion());
        erogacion.setAdicioncambioagno(erogacionActualizado.getAdicioncambioagno());
        erogacion.setAprfinal(erogacionActualizado.getAprfinal());
        erogacion.setRubro(erogacionActualizado.getRubro());
        erogacion.setCcosto(erogacionActualizado.getCcosto());

        return repository.save(erogacion);
    }

    public void eliminarErogacion(Integer iderogacionseg) {
        SegErogacion erogacion = repository.findById(iderogacionseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(erogacion);
    }
}
