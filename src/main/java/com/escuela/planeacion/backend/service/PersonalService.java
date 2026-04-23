package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Personal;
import com.escuela.planeacion.backend.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> getTodosPersonales() {
        return personalRepository.findAll();
    }

    public Personal getPersonalPorId(Integer idPersonal) {
        return personalRepository.findById(idPersonal)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
    }

    public List<Personal> getPersonalesPorActividad(Integer idActividad) {
        return personalRepository.findByIdactividad(idActividad);
    }

    public Personal crearPersonal(Personal personal) {
        return personalRepository.save(personal);
    }

    public Personal actualizarPersonal(Integer idPersonal, Personal personalActualizado) {
        Personal personal = personalRepository.findById(idPersonal)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

        personal.setNombreparticpprs(personalActualizado.getNombreparticpprs());
        personal.setCargoparticprs(personalActualizado.getCargoparticprs());
        personal.setValorprs(personalActualizado.getValorprs());
        personal.setIdactividad(personalActualizado.getIdactividad());
        personal.setEstado(personalActualizado.getEstado());

        return personalRepository.save(personal);
    }

    public void eliminarPersonal(Integer idPersonal) {
        Personal personal = personalRepository.findById(idPersonal)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
        personalRepository.delete(personal);
    }
}
