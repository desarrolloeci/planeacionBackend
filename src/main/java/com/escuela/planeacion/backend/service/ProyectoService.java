package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.dto.ProyectoDetalleDTO;
import com.escuela.planeacion.backend.entity.Proyecto;
import com.escuela.planeacion.backend.repository.ActividadRepository;
import com.escuela.planeacion.backend.repository.ArchivosRepository;
import com.escuela.planeacion.backend.repository.EjeProgramaProyectoRepository;
import com.escuela.planeacion.backend.repository.FinFactorCaractProyectoRepository;
import com.escuela.planeacion.backend.repository.IndicadorRepository;
import com.escuela.planeacion.backend.repository.ObjetivoRepository;
import com.escuela.planeacion.backend.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    private final ActividadRepository actividadRepository;
    private final ArchivosRepository archivosRepository;
    private final EjeProgramaProyectoRepository ejeRepo;
    private final IndicadorRepository indicadorRepository;
    private final ObjetivoRepository objetivoRepository;
    private final FinFactorCaractProyectoRepository finFactorRepo;

    public ProyectoService(ProyectoRepository proyectoRepository,
                           ActividadRepository actividadRepository,
                           ArchivosRepository archivosRepository,
                           EjeProgramaProyectoRepository ejeRepo,
                           IndicadorRepository indicadorRepository,
                           ObjetivoRepository objetivoRepository,
                           FinFactorCaractProyectoRepository finFactorRepo) {
        this.proyectoRepository = proyectoRepository;
        this.actividadRepository = actividadRepository;
        this.archivosRepository = archivosRepository;
        this.ejeRepo = ejeRepo;
        this.indicadorRepository = indicadorRepository;
        this.objetivoRepository = objetivoRepository;
        this.finFactorRepo = finFactorRepo;
    }

    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> obtenerPorId(Integer id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto crear(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizar(Integer id, Proyecto proyecto) {
        return proyectoRepository.findById(id)
                .map(existing -> {
                    proyecto.setIdproyecto(id);
                    return proyectoRepository.save(proyecto);
                })
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    public void eliminar(Integer id) {
        proyectoRepository.deleteById(id);
    }

    public Proyecto actualizarEstadoYObservacion(Integer idproyecto, Integer estadopr, String observacionpr) {
    return proyectoRepository.findById(idproyecto)
            .map(existing -> {
                existing.setEstadopr(estadopr);
                existing.setObservacionpr(observacionpr);
                return proyectoRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
}
    
    

    public Optional<ProyectoDetalleDTO> getProyectoDetalle(Integer idproyecto) {
        return proyectoRepository.findById(idproyecto).map(proyecto -> {
            ProyectoDetalleDTO dto = new ProyectoDetalleDTO();
            dto.setProyecto(proyecto);  // Asigna toda la entidad Proyecto

            dto.setActividades(actividadRepository.findByIdproyecto(idproyecto));
            dto.setArchivos(archivosRepository.findByIdproyecto(idproyecto));
            dto.setEjeProgramaProyectos(ejeRepo.findByIdproyecto(idproyecto));
            dto.setIndicadores(indicadorRepository.findByIdproyecto(idproyecto));
            dto.setObjetivos(objetivoRepository.findByIdproyecto(idproyecto));
            dto.setFinFactorCaractProyectos(finFactorRepo.findByIdproyecto(idproyecto));

            return dto;
        });
    }
    public String obtenerNombreProyecto(Integer idProyecto) {
        return proyectoRepository.findById(idProyecto)
                .map(Proyecto::getNombrepr) 
                .orElse("Proyecto sin nombre");
    }

public List<Proyecto> filtrarPorEstados(List<Integer> estados) {
    return proyectoRepository.findByEstadoprIn(estados);
}
}
