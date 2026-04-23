package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Archivos;
import com.escuela.planeacion.backend.repository.ArchivosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivosService {

    private final ArchivosRepository archivosRepository;

    public ArchivosService(ArchivosRepository archivosRepository) {
        this.archivosRepository = archivosRepository;
    }

    public List<Archivos> getAll() {
        return archivosRepository.findAll();
    }

    public Optional<Archivos> getById(Integer id) {
        return archivosRepository.findById(id);
    }

    public Archivos save(Archivos archivo) {
        return archivosRepository.save(archivo);
    }

    public Archivos update(Integer id, Archivos archivo) {
        archivo.setIdarchivo(id);
        return archivosRepository.save(archivo);
    }

    public void delete(Integer id) {
        archivosRepository.deleteById(id);
    }

    // Métodos adicionales
    public List<Archivos> getByIdActividad(Integer idactividad) {
        return archivosRepository.findByIdactividad(idactividad);
    }

    public List<Archivos> getByIdProyecto(Integer idproyecto) {
        return archivosRepository.findByIdproyecto(idproyecto);
    }
}
