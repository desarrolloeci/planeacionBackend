package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Responsable;
import com.escuela.planeacion.backend.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    public List<Responsable> getTodosResponsables() {
        return responsableRepository.findAll();
    }

    public Responsable getResponsablePorId(String codEmp) {
        return responsableRepository.findById(codEmp)
                .orElseThrow(() -> new RuntimeException("Responsable no encontrado"));
    }
}
