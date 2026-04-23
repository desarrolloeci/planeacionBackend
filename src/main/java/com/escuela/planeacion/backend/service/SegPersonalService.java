package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.SegPersonal;
import com.escuela.planeacion.backend.repository.SegPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegPersonalService {

    @Autowired
    private SegPersonalRepository repository;

    public List<SegPersonal> getTodosSegPersonal() {
        return repository.findAll();
    }

    public SegPersonal getSegPersonalPorId(Integer idsegpersonal) {
        return repository.findById(idsegpersonal)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<SegPersonal> getSegPersonalPorIdseguimiento(Integer idseguimiento) {
        return repository.findByIdseguimiento(idseguimiento);
    }

    public List<SegPersonal> getSegPersonalPorIdactividad(Integer idactividad) {
        return repository.findByIdactividad(idactividad);
    }

    public List<SegPersonal> getSegPersonalPorIdpersonal(Integer idpersonal) {
        return repository.findByIdpersonal(idpersonal);
    }

    public SegPersonal crearSegPersonal(SegPersonal segPersonal) {
        return repository.save(segPersonal);
    }

    public SegPersonal actualizarSegPersonal(Integer idsegpersonal, SegPersonal segPersonalActualizado) {
        SegPersonal segPersonal = repository.findById(idsegpersonal)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        segPersonal.setIdseguimiento(segPersonalActualizado.getIdseguimiento());
        segPersonal.setIdpersonal(segPersonalActualizado.getIdpersonal());
        segPersonal.setIdactividad(segPersonalActualizado.getIdactividad());
        segPersonal.setHoraseg(segPersonalActualizado.getHoraseg());
        segPersonal.setAgno(segPersonalActualizado.getAgno());
        segPersonal.setTipo(segPersonalActualizado.getTipo());

        return repository.save(segPersonal);
    }

    public void eliminarSegPersonal(Integer idsegpersonal) {
        SegPersonal segPersonal = repository.findById(idsegpersonal)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(segPersonal);
    }
}
