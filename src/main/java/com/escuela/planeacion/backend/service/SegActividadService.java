package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.SegActividad;
import com.escuela.planeacion.backend.repository.SegActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegActividadService {

    @Autowired
    private SegActividadRepository repository;

    public List<SegActividad> getTodasSegActividades() {
        return repository.findAll();
    }

    public SegActividad getSegActividadPorId(Integer idactividadseg) {
        return repository.findById(idactividadseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<SegActividad> getSegActividadesPorIdseguimiento(Integer idseguimiento) {
        return repository.findByIdseguimiento(idseguimiento);
    }

    public List<SegActividad> getSegActividadesPorIdactividad(Integer idactividad) {
        return repository.findByIdactividad(idactividad);
    }

    public SegActividad crearSegActividad(SegActividad segActividad) {
        return repository.save(segActividad);
    }

    public SegActividad actualizarSegActividad(Integer idactividadseg, SegActividad segActividadActualizado) {
        SegActividad segActividad = repository.findById(idactividadseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        segActividad.setIdactividad(segActividadActualizado.getIdactividad());
        segActividad.setIdseguimiento(segActividadActualizado.getIdseguimiento());
        segActividad.setEstadoejecactividad(segActividadActualizado.getEstadoejecactividad());
        segActividad.setDescripavance(segActividadActualizado.getDescripavance());
        segActividad.setAccionesact(segActividadActualizado.getAccionesact());
        segActividad.setPorcavanact(segActividadActualizado.getPorcavanact());

        return repository.save(segActividad);
    }

    public void eliminarSegActividad(Integer idactividadseg) {
        SegActividad segActividad = repository.findById(idactividadseg)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(segActividad);
    }
}
