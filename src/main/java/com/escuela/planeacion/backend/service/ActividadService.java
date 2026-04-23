package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Actividad;
import com.escuela.planeacion.backend.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public List<Actividad> getAll() {
        return actividadRepository.findAll();
    }

    public Optional<Actividad> getById(Integer id) {
        return actividadRepository.findById(id);
    }

    public Actividad save(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public Actividad update(Integer id, Actividad actividad) {
        actividad.setIdactividad(id);
        return actividadRepository.save(actividad);
    }

    public void delete(Integer id) {
        actividadRepository.deleteById(id);
    }
    
    public List<Actividad> getByProyectoId(Integer idproyecto) {
        return actividadRepository.findByIdproyecto(idproyecto);
    }
}