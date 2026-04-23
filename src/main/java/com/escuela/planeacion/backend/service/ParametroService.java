package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Parametro;
import com.escuela.planeacion.backend.repository.ParametroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {

    private final ParametroRepository parametroRepository;

    public ParametroService(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    public List<Parametro> listarTodos() {
        return parametroRepository.findAll();
    }

    public Optional<Parametro> obtenerPorId(Integer id) {
        return parametroRepository.findById(id);
    }

    public List<Parametro> listarPorTipo(String tipo) {
        return parametroRepository.findByTipo(tipo);
    }

    public Parametro crear(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    public Parametro actualizar(Integer id, Parametro parametro) {
        return parametroRepository.findById(id)
                .map(existing -> {
                    parametro.setIdparametro(id);
                    return parametroRepository.save(parametro);
                })
                .orElseThrow(() -> new RuntimeException("Parametro no encontrado"));
    }

    public void eliminar(Integer id) {
        parametroRepository.deleteById(id);
    }
}
