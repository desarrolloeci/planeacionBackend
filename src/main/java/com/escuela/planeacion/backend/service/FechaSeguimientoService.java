package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.FechaSeguimiento;
import com.escuela.planeacion.backend.repository.FechaSeguimientoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FechaSeguimientoService {

    @Autowired
    private FechaSeguimientoRepository repository;

    public List<FechaSeguimiento> getTodasFechas() {
        return repository.findAll();
    }

    public FechaSeguimiento crearFecha(FechaSeguimiento fecha) {
        return repository.save(fecha);
    }
    
    @Transactional
    public boolean cambiarFlag(String feciniseg, Integer nuevoFlag) {
        int filas = repository.actualizarFlag(feciniseg, nuevoFlag);
        return filas > 0;
    }
}
