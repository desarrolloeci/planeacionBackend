package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Plan;
import com.escuela.planeacion.backend.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> listarTodos() {
        return planRepository.findAll();
    }

    public Optional<Plan> obtenerPorId(Integer id) {
        return planRepository.findById(id);
    }

    public Plan crear(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan actualizar(Integer id, Plan plan) {
        return planRepository.findById(id)
                .map(existing -> {
                    plan.setIdplan(id);
                    return planRepository.save(plan);
                })
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

    public void eliminar(Integer id) {
        planRepository.deleteById(id);
    }
}
