package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.FactorFin;
import com.escuela.planeacion.backend.repository.FactorFinRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactorFinService {

    private final FactorFinRepository factorFinRepository;

    public FactorFinService(FactorFinRepository factorFinRepository) {
        this.factorFinRepository = factorFinRepository;
    }

    public List<FactorFin> listarTodos() {
        return factorFinRepository.findAll();
    }

    public Optional<FactorFin> obtenerPorId(Integer id) {
        return factorFinRepository.findById(id);
    }

    public FactorFin crear(FactorFin factorFin) {
        return factorFinRepository.save(factorFin);
    }

    public FactorFin actualizar(Integer id, FactorFin factorFin) {
        return factorFinRepository.findById(id)
                .map(existing -> {
                    factorFin.setIdfactor(id);
                    return factorFinRepository.save(factorFin);
                })
                .orElseThrow(() -> new RuntimeException("Factor Fin no encontrado"));
    }

    public void eliminar(Integer id) {
        factorFinRepository.deleteById(id);
    }

    public List<FactorFin> filtrar(FactorFin filtro) {
        Specification<FactorFin> spec = Specification.where(null);

        if (filtro.getIdfactor() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), filtro.getIdfactor()));
        }
        if (filtro.getNombrefacfin() != null && !filtro.getNombrefacfin().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nombre")), "%" + filtro.getNombrefacfin().toLowerCase() + "%"));
        }
        if (filtro.getSecuencial() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("secuencial"), filtro.getSecuencial()));
        }
        if (filtro.getRelacionfin() != null && !filtro.getRelacionfin().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("relacionFin")), "%" + filtro.getRelacionfin().toLowerCase() + "%"));
        }
        if (filtro.getRelacionfactor() != null && !filtro.getRelacionfactor().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("relacionFactor")), "%" + filtro.getRelacionfactor().toLowerCase() + "%"));
        }
        if (filtro.getFacintegral() != null && !filtro.getFacintegral().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("facIntegral")), "%" + filtro.getFacintegral().toLowerCase() + "%"));
        }
        if (filtro.getEje() != null && !filtro.getEje().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("eje")), "%" + filtro.getEje().toLowerCase() + "%"));
        }
        if (filtro.getTipofac() != null && !filtro.getTipofac().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("tipoFac")), "%" + filtro.getTipofac().toLowerCase() + "%"));
        }

        return factorFinRepository.findAll(spec);
    }
}
