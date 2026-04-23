package com.escuela.planeacion.backend.service;

import org.springframework.stereotype.Service;

import com.escuela.planeacion.backend.entity.Rubros;
import com.escuela.planeacion.backend.repository.RubrosRepository;

import java.util.List;

@Service
public class RubrosService {

    private final RubrosRepository repository;

    public RubrosService(RubrosRepository repository) {
        this.repository = repository;
    }
    
    public List<Rubros> getRubrosByCodCl1(String codCl1) {
        return repository.buscarPorCodCl1(codCl1);
    }

    public List<Rubros> getRubros() {
        return repository.findAll().stream().limit(1000).toList();
    }
}

