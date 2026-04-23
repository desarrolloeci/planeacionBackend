package com.escuela.planeacion.backend.service;

import org.springframework.stereotype.Service;

import com.escuela.planeacion.backend.entity.Snies;
import com.escuela.planeacion.backend.repository.SniesRepository;

import java.util.List;

@Service
public class SniesService {

    private final SniesRepository repository;

    public SniesService(SniesRepository repository) {
        this.repository = repository;
    }

    public List<Snies> getAll() {
        return repository.findAll().stream().limit(1000).toList();
    }
}