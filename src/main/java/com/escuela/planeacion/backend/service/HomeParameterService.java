package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.HomeParameter;
import com.escuela.planeacion.backend.repository.HomeParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeParameterService {

    private final HomeParameterRepository repository;

    public HomeParameterService(HomeParameterRepository repository) {
        this.repository = repository;
    }

    public List<HomeParameter> findAll() {
        return repository.findAll();
    }

    public Optional<HomeParameter> findById(Integer id) {
        return repository.findById(id);
    }

    public HomeParameter save(HomeParameter homeParameter) {
        return repository.save(homeParameter);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
